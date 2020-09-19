package com.twenk11k.sideprojects.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.twenk11k.sideprojects.newsapp.db.converter.SourceTypeConverter
import com.twenk11k.sideprojects.newsapp.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}