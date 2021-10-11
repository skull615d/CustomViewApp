package me.igorfedorov.myapp.common

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import me.igorfedorov.myapp.common.Constants.DEFAULT_THROTTLE_DELAY
import timber.log.Timber

fun View.setThrottledClickListener(delay: Long = 200L, onClick: (View) -> Unit) {
    setOnClickListener {
        throttle(delay) {
            onClick(it)
        }
    }
}

private var lastClickTimestamp = 0L
fun View.throttle(delay: Long = DEFAULT_THROTTLE_DELAY, action: () -> Unit): Boolean {
    val currentTimestamp = System.currentTimeMillis()
    val delta = currentTimestamp - lastClickTimestamp
    if (delta !in 0L..delay) {
        lastClickTimestamp = currentTimestamp
        action()
        return true
    }
    return false
}

@ExperimentalCoroutinesApi
fun EditText.textChangeFlow(): Flow<String> {
    return callbackFlow {
        val watcher = object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySendBlocking(s?.toString().orEmpty())
                    .onFailure { t -> Timber.d(t) }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        }
        this@textChangeFlow.addTextChangedListener(watcher)
        awaitClose {
            this@textChangeFlow.removeTextChangedListener(watcher)
        }
    }
}
