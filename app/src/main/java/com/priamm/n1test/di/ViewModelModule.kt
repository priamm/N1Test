package com.priamm.n1test.di

import androidx.lifecycle.ViewModel

import com.priamm.n1test.ui.offer.OfferViewModel
import com.priamm.n1test.ui.offers.OfferListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(OfferViewModel::class)
    abstract fun bindOfferViewModel(userViewModel: OfferViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OfferListViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: OfferListViewModel): ViewModel

}