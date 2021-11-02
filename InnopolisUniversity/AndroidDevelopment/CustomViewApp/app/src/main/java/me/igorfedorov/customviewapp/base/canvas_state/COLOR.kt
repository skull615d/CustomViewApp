package me.igorfedorov.customviewapp.base.canvas_state

import androidx.annotation.ColorRes
import me.igorfedorov.customviewapp.R

enum class COLOR(@ColorRes val value: Int) {
    BLACK(R.color.paint_black),
    RED(R.color.paint_red),
    GREEN(R.color.teal_200),
    BLUE(R.color.paint_blue);

    companion object {
        private val map = values().associateBy(COLOR::value)
        fun from(color: Int) = map[color] ?: BLACK
    }
}