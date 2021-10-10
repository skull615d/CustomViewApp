package me.igorfedorov.myapp.common

import android.view.View
import me.igorfedorov.myapp.common.Constants.DEFAULT_THROTTLE_DELAY

fun View.setThrottledClickListener(delay: Long = 200L, onClick: (View) -> Unit) {
    setOnClickListener {
        throttle(delay) {
            onClick(it)
        }
    }
}

private var lastClickTimestamp = 0L
private fun throttle(delay: Long = DEFAULT_THROTTLE_DELAY, action: () -> Unit): Boolean {
    val currentTimestamp = System.currentTimeMillis()
    val delta = currentTimestamp - lastClickTimestamp
    if (delta !in 0L..delay) {
        lastClickTimestamp = currentTimestamp
        action()
        return true
    }
    return false
}