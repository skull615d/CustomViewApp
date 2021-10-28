package me.igorfedorov.kinonline.feature.movie_info_screen.ui

import com.github.terrakok.cicerone.Router
import me.igorfedorov.kinonline.base.base_view_model.BaseViewModel
import me.igorfedorov.kinonline.base.base_view_model.Event

class MovieInfoViewModel(
    private val router: Router
) : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState()

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.OnPlayButtonCLick -> {

            }
        }
        return null
    }
}