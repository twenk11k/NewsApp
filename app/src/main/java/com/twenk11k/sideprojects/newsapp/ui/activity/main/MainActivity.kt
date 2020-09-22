package com.twenk11k.sideprojects.newsapp.ui.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.twenk11k.sideprojects.newsapp.R
import com.twenk11k.sideprojects.newsapp.common.DataBindingActivity
import com.twenk11k.sideprojects.newsapp.databinding.ActivityMainBinding
import com.twenk11k.sideprojects.newsapp.model.Article
import com.twenk11k.sideprojects.newsapp.ui.activity.webbrowser.WebBrowserActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity(), ArticleAdapterListener {

    // views
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonTryAgain: Button
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

    private fun setBindings() {
        binding.apply {
            vm = viewModel.apply {
                viewModel.fetchNews()
            }
        }
    }

    private fun setViews() {
        recyclerView = binding.recyclerView
        buttonTryAgain = binding.buttonTryAgain
        setRecyclerView()
        setClickListeners()
    }

    private fun setClickListeners() {
        buttonTryAgain.setOnClickListener {
            viewModel.fetchNews()
        }
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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun browseWebsite(url: String) {
        val intent = Intent(this@MainActivity, WebBrowserActivity::class.java)
        intent.putExtra("article_url",url)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main ,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_update_api_key) {
            displayUpdateApiKeyDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayUpdateApiKeyDialog() {
        MaterialDialog(this).show {
            title(R.string.update_api_key)
            input { _,text ->
                viewModel.apiKey = text.toString()
                viewModel.fetchNews()
            }
            positiveButton(R.string.update)
        }
    }

}