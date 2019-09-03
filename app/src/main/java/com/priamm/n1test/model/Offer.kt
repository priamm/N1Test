package com.priamm.n1test.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Offer(
    @SerializedName("_id")
    val id: Long,

    @SerializedName("photos")
    val photos: List<Photo>,

    @SerializedName("params")
    val params: Params

) : Parcelable