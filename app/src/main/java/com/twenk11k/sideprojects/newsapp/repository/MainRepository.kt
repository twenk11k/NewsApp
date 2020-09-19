package com.twenk11k.sideprojects.newsapp.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.twenk11k.sideprojects.newsapp.db.ArticleDao
import com.twenk11k.sideprojects.newsapp.network.NewsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val newsClient: NewsClient, private val articleDao: ArticleDao) {

    @WorkerThread
    suspend fun retrieveNewsResponse(onSuccess: () -> Unit, onError: (String) -> Unit) = flow {
        val articleList = articleDao.getArticleList()
        if(articleList.isEmpty()) {
            newsClient.fetchNewsResponse().apply {
                this.suspendOnSuccess {
                    data.whatIfNotNull {
                        if(it.articles != null) {
                            Log.d(javaClass.simpleName, "api called")
                            emit(it.articles)
                            articleDao.insertArticleList(it.articles)
                            onSuccess()
                        }
                    }
                }.onError {
                    onError(message())
                    Log.e(javaClass.simpleName,message())
                }.onException {
                    onError(message())
                    Log.e(javaClass.simpleName,message())
                }
            }
        } else {
            Log.d(javaClass.simpleName, "db called")
            emit(articleList)
            onSuccess()
        }
    }.flowOn(Dispatchers.IO)

}