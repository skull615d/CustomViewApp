package me.igorfedorov.kinonline.feature.movies_screen.ui

import me.igorfedorov.kinonline.base.base_view_model.BaseViewModel
import me.igorfedorov.kinonline.base.base_view_model.Event
import me.igorfedorov.kinonline.feature.movies_screen.domain.MoviesInteractor

class MoviesListViewModel(
    private val moviesInteractor: MoviesInteractor
) : BaseViewModel<ViewState>() {

    init {
        processUiEvent(UIEvent.OnGetMovies)
    }

    override fun initialViewState(): ViewState = ViewState(
        movies = emptyList(),
        errorMessage = null
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.OnGetMovies -> {
                moviesInteractor.getAllMovies().fold(
                    onError = {
                        processDataEvent(DataEvent.ErrorMoviesRequest(it.localizedMessage ?: ""))
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessMoviesRequest(it))
                    }
                )
            }
            is DataEvent.SuccessMoviesRequest -> {
                return previousState.copy(movies = event.movies)
            }
            is DataEvent.ErrorMoviesRequest -> {
                return previousState.copy(errorMessage = event.errorMessage)
            }
        }
        return null
    }

}