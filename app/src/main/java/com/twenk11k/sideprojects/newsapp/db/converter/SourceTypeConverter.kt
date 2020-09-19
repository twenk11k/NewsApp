package com.twenk11k.sideprojects.newsapp.db.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.twenk11k.sideprojects.newsapp.model.Article

open class SourceTypeConverter {

    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun fromString(value: String): Article.Source? {
        val adapter: JsonAdapter<Article.Source> = moshi.adapter(Article.Source::class.java)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromSourceType(type: Article.Source): String {
        val adapter: JsonAdapter<Article.Source> = moshi.adapter(Article.Source::class.java)
        return adapter.toJson(type)
    }

}