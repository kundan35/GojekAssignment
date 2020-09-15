package com.kotlin.gojek.assignment.presentation

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.kotlin.gojek.assignment.R
import com.kotlin.gojek.assignment.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper

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

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(base))
    }
}