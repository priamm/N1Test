package com.priamm.n1test.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(

    @SerializedName("position")
    val position: Long,

    @SerializedName("original")
    val source: String?,

    @SerializedName("360x270cp")
    val preview: String?
):Parcelable