package com.priamm.n1test.model

import com.google.gson.annotations.SerializedName

data class ResultSet(
    @SerializedName("limit")
    val limit: Int,

    @SerializedName("offset")
    val offset: Int,

    @SerializedName("count")
    val count: Int
)