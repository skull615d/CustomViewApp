package me.igorfedorov.newsfeedapp.feature.search_news_screen.ui

import me.igorfedorov.newsfeedapp.base.base_view_model.BaseViewModel
import me.igorfedorov.newsfeedapp.base.base_view_model.Event

class SearchNewsScreenViewModel : BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        articles = emptyList()
    )

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {

        }
        return null
    }

}