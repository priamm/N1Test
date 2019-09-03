package com.priamm.n1test.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(

    @SerializedName("street")
    val street: Street,

    @SerializedName("house_number")
    val houseNumber: String
): Parcelable