package com.twenk11k.sideprojects.newsapp.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import com.twenk11k.sideprojects.newsapp.network.NewsClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val newsClient: NewsClient) {

    @WorkerThread
    suspend fun retrieveNewsResponse(onError: (String) -> Unit) = flow {
        Log.d("asasdwevwev","called retrieveNewsResponse")
        newsClient.fetchNewsResponse().apply {
            this.suspendOnSuccess {
                data.whatIfNotNull {
                    emit(it)
                }
            }.onError {
                onError(message())
                Log.e("asasdwevwev",message())
            }.onException {
                onError(message())
                Log.e("asasdwevwev",message())
            }
        }
    }.flowOn(Dispatchers.IO)

}