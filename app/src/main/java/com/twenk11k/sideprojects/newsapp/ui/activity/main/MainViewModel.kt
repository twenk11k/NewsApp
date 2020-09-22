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
    val shouldDisplayError: ObservableBoolean = ObservableBoolean(false)

    var apiKey = "88df2868e58e4ccfa1c03cd53d41ceb9"

    init {
        newsLiveData = _newsLiveData.switchMap {
            isLoading.set(true)
            shouldDisplayError.set(false)
            launchOnViewModelScope {
                this.mainRepository.retrieveNewsResponse(
                    apiKey,
                    onSuccess = {
                        isLoading.set(false)
                        shouldDisplayError.set(false)
                    },
                    onError = {
                        isLoading.set(false)
                        shouldDisplayError.set(true)
                        _toast.postValue(it) }
                ).asLiveData()
            }
        }
    }

    @MainThread
    fun fetchNews() {
        _newsLiveData.value = true
    }

}