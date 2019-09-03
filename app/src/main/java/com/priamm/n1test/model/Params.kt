package com.priamm.n1test.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Params(

    @SerializedName("price")
    val price: Long,

    @SerializedName("rooms_count")
    val roomsCount: Int?,

    @SerializedName("house_addresses")
    val address: List<Address>?,

    @SerializedName("total_area") val
    totalArea: Int?,

    @SerializedName("living_area")
    val livingArea: Int?,

    @SerializedName("kitchen_area")
    val kitchenArea: Int?,

    @SerializedName("floors_count")
    val floorsCount: Int?,

    @SerializedName("floor")
    val floor: Int?
):Parcelable