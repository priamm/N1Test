package com.priamm.n1test.model

import com.google.gson.annotations.SerializedName

data class ResponseOffers(

    @SerializedName("result")
    val result: List<Offer>,

    @SerializedName("metadata")
    val metadata: Metadata
)