package com.twenk11k.sideprojects.newsapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.twenk11k.sideprojects.newsapp.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleList(articleList: List<Article>)

    @Query("SELECT * FROM article")
    suspend fun getArticleList(): List<Article>

}