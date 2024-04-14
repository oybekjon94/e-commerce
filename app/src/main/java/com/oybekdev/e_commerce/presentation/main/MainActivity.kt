package com.oybekdev.e_commerce.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.oybekdev.e_commerce.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContentView(R.layout.activity_main)
    }
}