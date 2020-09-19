package com.twenk11k.sideprojects.newsapp.extension

import android.view.View

fun View.gone(shouldBeGone: Boolean) {
    visibility = if (shouldBeGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
