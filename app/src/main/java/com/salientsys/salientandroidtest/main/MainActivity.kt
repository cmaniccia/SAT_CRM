package com.salientsys.salientandroidtest.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.salientsys.salientandroidtest.R
import com.salientsys.salientandroidtest.SalientTestApplication

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as SalientTestApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}