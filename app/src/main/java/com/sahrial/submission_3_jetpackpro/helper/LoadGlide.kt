package com.sahrial.submission_3_jetpackpro.helper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sahrial.submission_3_jetpackpro.R

fun ImageView.loadUrl(path: String) {
    Glide.with(this).clear(this)
    Glide.with(this)
        .setDefaultRequestOptions(RequestOptions()
            .placeholder(R.drawable.ic_baseline_image_24)
            .error(R.drawable.ic_baseline_image_24)
    ).load(path).into(this)
}