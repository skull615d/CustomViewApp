package me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui

import me.igorfedorov.newsfeedapp.base.base_view_model.Event
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

data class ViewState(
    val articleList: List<Article>,
    val article: Article?,
    val isLoading: Boolean,
    val errorMessage: String?
) {
    val isInErrorState = errorMessage != null
}

sealed class UIEvent() : Event {
    object GetCurrentNews : UIEvent()
    data class OnArticleCLick(val article: Article) : UIEvent()
    object OnGoBackFromWebView : UIEvent()
}

sealed class DataEvent() : Event {
    object OnLoadDataTrue : DataEvent()
    object OnLoadDataFalse : DataEvent()
    data class SuccessNewsRequest(val articleList: List<Article>) : DataEvent()
    data class ErrorNewsRequest(val errorMessage: String) : DataEvent()
}