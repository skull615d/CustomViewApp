package me.igorfedorov.kinonline.feature.video_player_screen.ui

import com.github.terrakok.cicerone.Router
import com.google.android.exoplayer2.ExoPlayer
import me.igorfedorov.kinonline.base.base_view_model.BaseViewModel
import me.igorfedorov.kinonline.base.base_view_model.Event

class VideoPlayerViewModel(
    private val router: Router,
    private val exoPlayer: ExoPlayer
) : BaseViewModel<ViewState>() {

    override fun initialViewState() = ViewState(
        movieUrl = ""
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

        }
        return null
    }
}