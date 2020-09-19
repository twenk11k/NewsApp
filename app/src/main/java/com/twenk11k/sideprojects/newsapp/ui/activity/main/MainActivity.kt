package com.twenk11k.sideprojects.newsapp.ui.activity.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twenk11k.sideprojects.newsapp.R
import com.twenk11k.sideprojects.newsapp.common.DataBindingActivity
import com.twenk11k.sideprojects.newsapp.databinding.ActivityMainBinding
import com.twenk11k.sideprojects.newsapp.model.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity(), ArticleAdapterListener {

    // views
    private lateinit var recyclerView: RecyclerView

    private var listArticle = arrayListOf<Article>()
    private lateinit var adapterArticle: ArticleAdapter

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    @VisibleForTesting val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        setViews()
        handleViewModel()
    }

    private fun setViews() {
        recyclerView = binding.recyclerView
        setRecyclerView()
    }

    private fun setRecyclerView() {
        setRecyclerViewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = adapterArticle
    }


    private fun setRecyclerViewAdapter() {
        adapterArticle = ArticleAdapter(this@MainActivity, listArticle, this)
    }


    private fun handleViewModel() {
        viewModel.toast.observe(this) {
            displayToastMessage(it)
        }

        viewModel.newsLiveData.observe(this@MainActivity, {
            if(it != null)
                updateArticleAdapter(it)
        })
    }

    private fun updateArticleAdapter(articles: List<Article>) {
        this.listArticle.clear()
        this.listArticle.addAll(articles)
        adapterArticle.notifyDataSetChanged()
    }

    private fun displayToastMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun setBindings() {
        binding.apply {
            vm = viewModel.apply {
                viewModel.fetchNews()
            }
        }
    }

    override fun browseWebsite(url: String) {

    }

}