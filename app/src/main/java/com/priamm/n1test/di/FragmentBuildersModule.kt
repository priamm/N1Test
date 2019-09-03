package com.priamm.n1test.di

import com.priamm.n1test.ui.offer.OfferFragment
import com.priamm.n1test.ui.offers.OfferListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeOfferListFragment(): OfferListFragment

    @ContributesAndroidInjector
    abstract fun contributeOfferFragment(): OfferFragment

}