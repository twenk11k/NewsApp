package com.twenk11k.sideprojects.newsapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Source(
    @field:Json(name = "name") val id: String?
) : Parcelable