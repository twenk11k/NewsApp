package com.twenk11k.sideprojects.newsapp.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @field:Json(name = "status") val status: String?,
    @field:Json(name = "totalResults") val totalResults: Int?,
    @field:Json(name = "articles") val articles: List<Article>?,
)