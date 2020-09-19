package com.twenk11k.sideprojects.newsapp.ui.activity.webbrowser

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebChromeClient.CustomViewCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import com.twenk11k.sideprojects.newsapp.R
import com.twenk11k.sideprojects.newsapp.common.DataBindingActivity
import com.twenk11k.sideprojects.newsapp.databinding.ActivityWebBrowserBinding


class WebBrowserActivity: DataBindingActivity() {

    // views
    private lateinit var webView: WebView
    private lateinit var customViewContainer: FrameLayout
    private var customView: View? = null

    private var customViewCallback: CustomViewCallback? = null
    private var webChromeClient: CustomWebChromeClient? = null
    private var url = ""

    private val binding: ActivityWebBrowserBinding by binding(R.layout.activity_web_browser)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = intent.getStringExtra("article_url").toString()
        setViews()
    }

    private fun setViews() {
        webView = binding.webView
        customViewContainer = binding.customViewContainer
        setActionBarOptions()
        setWebView()
    }

    private fun setActionBarOptions() {
        supportActionBar?.title = url
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = CustomWebChromeClient()
        webView.settings.builtInZoomControls = true;
        webView.loadUrl(url)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onStop() {
        super.onStop()
        if (inCustomView()) {
            hideCustomView()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (inCustomView()) {
                hideCustomView()
                return true
            }
            if ((customView == null) && webView.canGoBack()) {
                webView.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun inCustomView(): Boolean {
        return customView != null
    }

    private fun hideCustomView() {
        webChromeClient?.onHideCustomView()
    }

    inner class CustomWebChromeClient: WebChromeClient() {

        override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
            if (customView != null) {
                callback?.onCustomViewHidden()
                return
            }
            customView = view
            webView.visibility = View.GONE
            customViewContainer.visibility = View.VISIBLE
            customViewContainer.addView(view)
            customViewCallback = callback
        }

        override fun onHideCustomView() {
            super.onHideCustomView()
            if (customView == null)
                return

            webView.visibility = View.VISIBLE
            customViewContainer.visibility = View.GONE

            customView?.visibility = View.GONE

            customViewContainer.removeView(customView)
            customViewCallback?.onCustomViewHidden()

            customView = null
        }

    }

}