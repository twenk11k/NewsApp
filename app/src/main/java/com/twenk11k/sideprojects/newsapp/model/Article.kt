package com.twenk11k.sideprojects.newsapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "article")
@JsonClass(generateAdapter = true)
data class Article(
    @field:Json(name = "source") val source: Source?,
    @field:Json(name = "author") val author: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "url") @PrimaryKey val url: String,
    @field:Json(name = "urlToImage") val urlToImage: String?,
    @field:Json(name = "publishedAt") val publishedAt: String?
) {
    @JsonClass(generateAdapter = true)
    data class Source(
        @field:Json(name = "name") val id: String?
    )
}