package com.kotlin.gojek.assignment.data

import android.content.SharedPreferences
import java.util.*

object Util {
    fun dateGreaterThanExpireTime(
        sharedPreferences: SharedPreferences
    ): Boolean {
        try {
            val fetchTime: Long = sharedPreferences.getLong("lastDw", 0L)
            val diff: Long = Calendar.getInstance().time.time - fetchTime
            val diffMinutes: Long = diff / (60 * 1000) % 60
            return (diffMinutes < 120 && fetchTime != 0L)
        } catch (e1: Exception) {
            return true
        }

    }
}