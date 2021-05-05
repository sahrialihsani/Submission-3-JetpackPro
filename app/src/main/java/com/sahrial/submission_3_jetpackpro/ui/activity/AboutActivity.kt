package com.sahrial.submission_3_jetpackpro.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sahrial.submission_3_jetpackpro.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.title ="About Developer"
    }
}