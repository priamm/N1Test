package com.priamm.n1test.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.priamm.n1test.data.network.OffersAPI
import com.priamm.n1test.model.Offer
import javax.inject.Inject

class OfferRepositoryFactory @Inject constructor(
    private val offersAPI: OffersAPI
) : DataSource.Factory<Int, Offer>() {

    val offerLiveData = MutableLiveData<OfferRepository>()

    override fun create(): DataSource<Int, Offer> {
        val offerDataSource = OfferRepository(offersAPI)
        offerLiveData.postValue(offerDataSource)
        return offerDataSource
    }
}