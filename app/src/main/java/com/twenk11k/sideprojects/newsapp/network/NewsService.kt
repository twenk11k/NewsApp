package com.twenk11k.sideprojects.newsapp.network

import com.skydoves.sandwich.ApiResponse
import com.twenk11k.sideprojects.newsapp.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("everything?q=bitcoin&from=2020-09-17&sortBy=publishedAt")
    suspend fun fetchNewsResponse(@Query("apiKey") apiKey: String): ApiResponse<NewsResponse>

}