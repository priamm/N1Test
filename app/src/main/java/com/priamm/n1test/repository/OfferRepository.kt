package com.priamm.n1test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PositionalDataSource
import com.priamm.n1test.utility.Constants
import com.priamm.n1test.data.network.OffersAPI
import com.priamm.n1test.model.Offer
import com.priamm.n1test.model.ResponseOffers
import com.priamm.n1test.extensions.add
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

import javax.inject.Inject

class OfferRepository @Inject constructor(private val offersAPI: OffersAPI) : PositionalDataSource<Offer>() {

    val firstLoading: LiveData<Boolean>
        get() = firstLoadingLiveData

    private val compositeDisposable = CompositeDisposable()
    private val firstLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    private val nextLiveData: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Offer>) {
        getOffers(params.loadSize, params.startPosition)
            .doOnSubscribe { nextLiveData.postValue(true) }
            .subscribe({
                nextLiveData.postValue(false)
                callback.onResult(it.result)
            }, {
                nextLiveData.postValue(false)
            })
            .add(compositeDisposable)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Offer>) {
        getOffers(params.requestedLoadSize, params.requestedStartPosition)
            .doOnSubscribe { firstLoadingLiveData.postValue(true) }
            .subscribe({
                firstLoadingLiveData.postValue(false)
                callback.onResult(it.result, 0, it.metadata.resultset.count)
            }, {
                firstLoadingLiveData.postValue(false)
            })
            .add(compositeDisposable)
    }

    fun clear() {
        compositeDisposable.dispose()
    }

    private fun getOffers(limit: Int, offset: Int): Single<ResponseOffers> {
        return offersAPI
            .getOffers(
                Constants.FILTER_REGION,
                Constants.FILTER_TYPE,
                Constants.FILTER_RUBRIC,
                Constants.FILTER_STATUS,
                Constants.FILTER_SORT,
                limit,
                offset
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}