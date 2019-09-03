package com.priamm.n1test.ui.offer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.priamm.n1test.model.Offer
import javax.inject.Inject

class OfferViewModel @Inject constructor() : ViewModel() {

    val offer: LiveData<Offer>
        get() = offerLiveData
    private val offerLiveData = MutableLiveData<Offer>()

    fun init(offer: Offer) {
        offerLiveData.value = offer
    }

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return OfferViewModel() as T
        }
    }
}
