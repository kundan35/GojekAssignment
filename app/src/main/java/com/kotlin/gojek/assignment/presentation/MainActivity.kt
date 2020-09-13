package com.kotlin.gojek.assignment.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kotlin.gojek.assignment.R
import com.kotlin.gojek.assignment.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
            )
        setSupportActionBar(findViewById(R.id.toolbar))
    }
}