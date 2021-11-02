package me.igorfedorov.customviewapp.feature.canvas

import me.igorfedorov.customviewapp.ToolsItem
import me.igorfedorov.customviewapp.base.base_view_model.Event
import me.igorfedorov.customviewapp.base.canvas_state.LINE
import me.igorfedorov.customviewapp.base.canvas_state.SIZE

data class ViewState(
    val colors: List<ToolsItem.ColorModel>,
    val sizes: List<ToolsItem.SizeModel>,
    val lines: List<ToolsItem.LineModel>,
    val isToolsVisible: Boolean,
    val canvasViewState: CanvasViewState,
)

sealed class UIEvent : Event {
    object OnToolsClicked : UIEvent()
    object OnShowPaletteClicked : UIEvent()
    data class OnColorClicked(val index: Int) : UIEvent()
    data class OnSizeClicked(val size: SIZE) : UIEvent()
    data class OnLineClicked(val line: LINE) : UIEvent()
}