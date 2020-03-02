package com.priamm.n1test.data.network

import com.priamm.n1test.utility.Constants
import com.priamm.n1test.model.ResponseOffers
import io.reactivex.rxjava3.core.Single

import retrofit2.http.GET
import retrofit2.http.Query

interface OffersAPI {

    @GET(Constants.OFFERS)
    fun getOffers(
        @Query(Constants.FILTER) filter: String,
        @Query(Constants.DEAL_TYPE) query: String,
        @Query(Constants.RUBRIC) rubric: String,
        @Query(Constants.STATUS) status: String,
        @Query(Constants.SORT) sort: String,
        @Query(Constants.LIMIT) limit: Int,
        @Query(Constants.OFFSET) offset: Int
    ): Single<ResponseOffers>
}