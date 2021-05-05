package com.sahrial.submission_3_jetpackpro.adapter.callback

import com.sahrial.submission_3_jetpackpro.data.source.local.entity.MovEntity

interface MovieCallback {
    fun itemClicked(item: MovEntity)
}