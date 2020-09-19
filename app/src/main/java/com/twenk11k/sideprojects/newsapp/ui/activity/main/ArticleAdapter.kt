package com.twenk11k.sideprojects.newsapp.ui.activity.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.twenk11k.sideprojects.newsapp.R
import com.twenk11k.sideprojects.newsapp.databinding.AdapterItemArticleBinding
import com.twenk11k.sideprojects.newsapp.model.Article

class ArticleAdapter(private val context: Context, private var listArticle: List<Article>, private var articleAdapterListener: ArticleAdapterListener): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        val binding: AdapterItemArticleBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_item_article,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listArticle.size
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) {
        holder.bind(listArticle[holder.adapterPosition])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    inner class ViewHolder(val binding: AdapterItemArticleBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(article: Article) {
            if(article.urlToImage != null)
                setImageArticle(article.urlToImage)
            if(article.title != null)
                binding.textTitle.text = article.title
            if(article.description != null)
                binding.textDescription.text = article.description
            if(article.source != null)
                binding.textSource.text = article.source.name
            binding.root.setOnClickListener(this)
        }

        private fun setImageArticle(imageUrl: String) {
            Glide.with(context)
                .load(imageUrl)
                .into(binding.imageArticle)
        }

        override fun onClick(v: View) {
            articleAdapterListener.browseWebsite(listArticle[adapterPosition].url)
        }

    }

}