package com.priamm.n1test.model

import com.google.gson.annotations.SerializedName

data class Metadata(

    @SerializedName("resultset")
    val resultset: ResultSet

)