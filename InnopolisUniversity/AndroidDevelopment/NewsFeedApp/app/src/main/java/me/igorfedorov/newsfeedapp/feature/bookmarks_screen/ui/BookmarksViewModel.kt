package me.igorfedorov.newsfeedapp.feature.bookmarks_screen.ui

import me.igorfedorov.newsfeedapp.base.base_view_model.BaseViewModel
import me.igorfedorov.newsfeedapp.base.base_view_model.Event
import me.igorfedorov.newsfeedapp.feature.bookmarks_screen.domain.BookmarksInteractor

class BookmarksViewModel(
    private val bookmarksInteractor: BookmarksInteractor
) : BaseViewModel<ViewState>() {

    init {
        processUiEvent(UIEvent.GetCurrentBookmarks)
    }

    override fun initialViewState(): ViewState {
        return ViewState(
            articles = emptyList()
        )
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.GetCurrentBookmarks -> {
                processUiEvent(DataEvent.OnLoadData)
                bookmarksInteractor.read().fold(
                    onError = {
                        processDataEvent(DataEvent.ErrorBookmarksRequest(it.localizedMessage ?: ""))
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessBookmarksRequest(it))
                    }
                )
            }
            is DataEvent.SuccessBookmarksRequest -> {
                return previousState.copy(articles = event.articles)
            }
        }
        return null
    }
}