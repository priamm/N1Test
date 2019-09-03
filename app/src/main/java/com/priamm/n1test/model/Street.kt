package com.priamm.n1test.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Street(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name_seo")
    val nameSeo: String,

    @SerializedName("name_ru")
    val nameRu: String,

    @SerializedName("name_translit")
    val nameTranslit: String,

    @SerializedName("name_raw_ru")
    val nameRawRu: String,

    @SerializedName("abbr_raw_ru")
    val abbrRawRu: String
):Parcelable