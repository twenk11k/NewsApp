package com.twenk11k.sideprojects.newsapp.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Article(
    @field:Json(name = "source") val source: Source?,
    @field:Json(name = "author") val author: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "urlToImage") val urlToImage: String?,
    @field:Json(name = "publishedAt") val publishedAt: String?
) : Parcelable