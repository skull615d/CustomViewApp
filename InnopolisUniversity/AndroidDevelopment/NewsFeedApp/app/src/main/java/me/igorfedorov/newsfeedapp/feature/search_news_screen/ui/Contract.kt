package me.igorfedorov.newsfeedapp.feature.search_news_screen.ui

import me.igorfedorov.newsfeedapp.base.base_view_model.Event
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article
import me.igorfedorov.newsfeedapp.feature.search_news_screen.ui.utils.SortOrder

data class ViewState(
    val articles: List<Article>
)

sealed class UIEvent() : Event {
    object GetArticles : UIEvent()
    data class OnSearchTextInput(val text: String) : UIEvent()
    data class OnSortOrderPicked(val sortOrder: SortOrder) : UIEvent()
}

sealed class DataEvent() : Event {
    object OnLoadData : DataEvent()
    data class SuccessResponse(val articles: List<Article>) : DataEvent()
    data class ErrorResponse(val errorMessage: String) : DataEvent()
}

