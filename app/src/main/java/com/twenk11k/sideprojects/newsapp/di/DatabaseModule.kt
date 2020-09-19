package com.twenk11k.sideprojects.newsapp.di

import android.app.Application
import androidx.room.Room
import com.twenk11k.sideprojects.newsapp.R
import com.twenk11k.sideprojects.newsapp.db.AppDatabase
import com.twenk11k.sideprojects.newsapp.db.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            application.getString(R.string.database)
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(appDatabase: AppDatabase): ArticleDao {
        return appDatabase.articleDao()
    }

}