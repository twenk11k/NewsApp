package com.twenk11k.sideprojects.newsapp.ui.activity.main

import androidx.annotation.MainThread
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.twenk11k.sideprojects.newsapp.model.Article
import com.twenk11k.sideprojects.newsapp.repository.MainRepository
import com.twenk11k.sideprojects.newsapp.ui.activity.LiveCoroutinesViewModel

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository): LiveCoroutinesViewModel() {

    private var _newsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val newsLiveData: LiveData<List<Article>>

    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = _toast

    init {
        newsLiveData = _newsLiveData.switchMap {
            launchOnViewModelScope {
                this.mainRepository.retrieveNewsResponse(
                    onError = { _toast.postValue(it) }
                ).asLiveData()
            }
        }
    }

    /*suspend fun retrieveNewsResponse(): LiveData<NewsResponse> {
        return mainRepository.retrieveNewsResponse(onError = { displayToast(it)}).asLiveData()
    }

     */

    @MainThread
    fun fetchNews() {
        _newsLiveData.value = true
    }


}