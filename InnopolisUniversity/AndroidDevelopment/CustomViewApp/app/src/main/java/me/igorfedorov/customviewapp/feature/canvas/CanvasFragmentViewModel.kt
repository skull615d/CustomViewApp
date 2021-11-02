package me.igorfedorov.customviewapp.feature.canvas

import me.igorfedorov.customviewapp.ToolsItem
import me.igorfedorov.customviewapp.base.COLOR
import me.igorfedorov.customviewapp.base.base_view_model.BaseViewModel
import me.igorfedorov.customviewapp.base.base_view_model.Event

class CanvasFragmentViewModel : BaseViewModel<ViewState>() {

    override fun initialViewState() = ViewState(
        colors = enumValues<COLOR>().map { ToolsItem.ColorModel(it.value) },
        isPaletteVisible = false,
        canvasViewState = CanvasViewState(
            color = COLOR.BLACK
        )
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.OnToolsClicked -> {
                return previousState.copy(isPaletteVisible = !previousState.isPaletteVisible)
            }
            is UIEvent.OnColorClicked -> {
                return previousState.copy(
                    canvasViewState = previousState.canvasViewState.copy(
                        color = COLOR.from(
                            previousState.colors[event.index].color
                        )
                    ),
                    isPaletteVisible = !previousState.isPaletteVisible
                )
            }
            is UIEvent.OnShowPaletteClicked -> {
                return previousState.copy(isPaletteVisible = !previousState.isPaletteVisible)
            }
        }
        return null
    }
}