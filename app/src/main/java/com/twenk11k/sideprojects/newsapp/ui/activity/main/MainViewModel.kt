package com.twenk11k.sideprojects.newsapp.ui.activity.main

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.twenk11k.sideprojects.newsapp.common.LiveCoroutinesViewModel
import com.twenk11k.sideprojects.newsapp.model.Article
import com.twenk11k.sideprojects.newsapp.repository.MainRepository

class MainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository): LiveCoroutinesViewModel() {

    private var _newsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val newsLiveData: LiveData<List<Article>>

    private val _toast: MutableLiveData<String> = MutableLiveData()
    val toast: LiveData<String> get() = _toast

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    init {
        newsLiveData = _newsLiveData.switchMap {
            isLoading.set(true)
            launchOnViewModelScope {
                this.mainRepository.retrieveNewsResponse(
                    onSuccess = { isLoading.set(false) },
                    onError = { _toast.postValue(it) }
                ).asLiveData()
            }
        }
    }

    @MainThread
    fun fetchNews() {
        _newsLiveData.value = true
    }

}