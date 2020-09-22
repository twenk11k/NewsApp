package com.twenk11k.sideprojects.newsapp.network

import javax.inject.Inject

class NewsClient @Inject constructor(private val newsService: NewsService) {

    suspend fun fetchNewsResponse(apiKey: String) =
        newsService.fetchNewsResponse(apiKey)

}