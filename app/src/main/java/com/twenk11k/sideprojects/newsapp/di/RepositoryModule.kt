package com.twenk11k.sideprojects.newsapp.di

import com.twenk11k.sideprojects.newsapp.network.NewsClient
import com.twenk11k.sideprojects.newsapp.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideMainRepository(newsClient: NewsClient): MainRepository {
        return MainRepository(newsClient)
    }

}