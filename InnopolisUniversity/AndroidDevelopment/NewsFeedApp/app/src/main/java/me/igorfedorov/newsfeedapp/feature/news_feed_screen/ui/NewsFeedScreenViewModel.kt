package me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui

import me.igorfedorov.newsfeedapp.base.BaseViewModel
import me.igorfedorov.newsfeedapp.base.Event
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.NewsFeedInteractor

class NewsFeedScreenViewModel(
    private val newsFeedInteractor: NewsFeedInteractor
) : BaseViewModel<ViewState>() {

    init {
        processUiEvent(UIEvent.GetCurrentNews)
    }

    override fun initialViewState(): ViewState {
        return ViewState(emptyList(), false)
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.GetCurrentNews -> {
                processDataEvent(DataEvent.OnLoadData)
                newsFeedInteractor.getLastHourNews().fold(
                    onError = {
                        processDataEvent(DataEvent.ErrorNewsRequest(it.localizedMessage ?: ""))
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessNewsRequest(it))
                    }
                )
            }
            is DataEvent.OnLoadData -> {
                return previousState.copy(isLoading = true)
            }
            is DataEvent.SuccessNewsRequest -> {
                return previousState.copy(articleList = event.articleList, isLoading = false)
            }
            is DataEvent.ErrorNewsRequest -> {
            }

        }
        return null
    }


}