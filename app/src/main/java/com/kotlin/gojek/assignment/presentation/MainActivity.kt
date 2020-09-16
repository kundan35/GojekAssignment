package com.kotlin.gojek.assignment.presentation

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
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
        initUI()
    }

    private fun initUI() {
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            val mTitleTextView = AppCompatTextView(applicationContext)
            mTitleTextView.setSingleLine()
            val layoutParams =
                ActionBar.LayoutParams(
                    ActionBar.LayoutParams.WRAP_CONTENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
            layoutParams.gravity = Gravity.CENTER
            actionBar.setCustomView(mTitleTextView, layoutParams)
            actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            mTitleTextView.text = getString(R.string.trending)
            mTitleTextView.setTextColor(ContextCompat.getColor(applicationContext, R.color.header))
            mTitleTextView.textSize = 21.0F
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(base))
    }
}