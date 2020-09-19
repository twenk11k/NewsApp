package com.twenk11k.sideprojects.newsapp.network

import com.skydoves.sandwich.ApiResponse
import com.twenk11k.sideprojects.newsapp.model.NewsResponse
import retrofit2.http.GET

interface NewsService {

    @GET("everything?q=bitcoin&from=2020-08-19&sortBy=publishedAt&apiKey=88df2868e58e4ccfa1c03cd53d41ceb9")
    suspend fun fetchNewsResponse(): ApiResponse<NewsResponse>

}