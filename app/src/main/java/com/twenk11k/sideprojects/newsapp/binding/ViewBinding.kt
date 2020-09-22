package com.twenk11k.sideprojects.newsapp.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.twenk11k.sideprojects.newsapp.extension.gone

@BindingAdapter("gone")
fun bindGone(view: View, shouldBeGone: Boolean) {
    view.gone(shouldBeGone)
}
