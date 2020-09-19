package com.twenk11k.sideprojects.newsapp.ui.activity.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.twenk11k.sideprojects.newsapp.R
import com.twenk11k.sideprojects.newsapp.databinding.ActivityMainBinding
import com.twenk11k.sideprojects.newsapp.ui.activity.DataBindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {

    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    @VisibleForTesting val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBindings()
        handleViewModel()
    }

    private fun handleViewModel() {
        viewModel.toast.observe(this) {
            displayToastMessage(it)
        }

        viewModel.newsLiveData.observe(this@MainActivity, {
            if(it != null)
                Log.d(javaClass.simpleName, it.toString())
        })
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

}