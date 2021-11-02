package me.igorfedorov.customviewapp

import androidx.annotation.ColorRes
import me.igorfedorov.customviewapp.base.Item
import me.igorfedorov.customviewapp.base.canvas_state.LINE
import me.igorfedorov.customviewapp.base.canvas_state.SIZE

sealed class ToolsItem : Item {

    data class ColorModel(@ColorRes val color: Int) : ToolsItem()
    data class SizeModel(val size: SIZE) : ToolsItem()
    data class LineModel(val line: LINE) : ToolsItem()

}
