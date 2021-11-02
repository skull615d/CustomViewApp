package me.igorfedorov.customviewapp.feature.canvas

import me.igorfedorov.customviewapp.base.canvas_state.COLOR
import me.igorfedorov.customviewapp.base.canvas_state.LINE
import me.igorfedorov.customviewapp.base.canvas_state.SIZE

data class CanvasViewState(
    val color: COLOR,
    val size: SIZE,
    val line: LINE
)
