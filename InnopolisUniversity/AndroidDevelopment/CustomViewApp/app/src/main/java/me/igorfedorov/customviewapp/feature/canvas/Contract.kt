package me.igorfedorov.customviewapp.feature.canvas

import me.igorfedorov.customviewapp.ToolsItem
import me.igorfedorov.customviewapp.base.base_view_model.Event

data class ViewState(
    val colors: List<ToolsItem.ColorModel>,
    val isPaletteVisible: Boolean,
    val canvasViewState: CanvasViewState,
)

sealed class UIEvent : Event {
    object OnToolsClicked : UIEvent()
    data class OnColorClicked(val index: Int) : UIEvent()
}