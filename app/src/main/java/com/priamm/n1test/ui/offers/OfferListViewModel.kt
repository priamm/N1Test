package com.priamm.n1test.ui.offers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.priamm.n1test.model.Offer
import com.priamm.n1test.repository.OfferRepositoryFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable

import javax.inject.Inject

class OfferListViewModel @Inject constructor(private val offersRepositoryFactory: OfferRepositoryFactory) :
    ViewModel() {

    val offers: LiveData<PagedList<Offer>> = LivePagedListBuilder(offersRepositoryFactory, 20).build()
    val isInitLoading: LiveData<Boolean>
        get() = Transformations.switchMap(offersRepositoryFactory.offerLiveData) { it.firstLoading }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        offersRepositoryFactory.offerLiveData.value?.clear()
    }

    fun refresh() {
        offersRepositoryFactory.offerLiveData.value?.invalidate()
    }


    @Suppress("UNCHECKED_CAST")
    class Factory(private val offersDataSourceFactory: OfferRepositoryFactory) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return OfferListViewModel(offersDataSourceFactory) as T
        }
    }
}
