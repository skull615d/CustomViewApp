package me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui

import me.igorfedorov.newsfeedapp.base.base_view_model.Event
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

data class ViewState(
    val articleList: List<Article>,
    val article: Article?,
    val isLoading: Boolean,
    val errorMessage: String?,
    val toastMessage: String?
) {
    val isInErrorState = errorMessage != null
}

sealed class UIEvent() : Event {
    object GetCurrentNews : UIEvent()
    object OnGoBackFromWebView : UIEvent()
    data class OnArticleCLick(val article: Article) : UIEvent()
    data class OnBookmarkClick(val article: Article) : UIEvent()
    data class ShowToast(val toastMessage: String) : UIEvent()
    object OnConfigurationChanged : UIEvent()
}

sealed class DataEvent() : Event {
    object OnLoadDataTrue : DataEvent()
    object OnLoadDataFalse : DataEvent()
    data class SuccessNewsRequest(val articleList: List<Article>) : DataEvent()
    data class ErrorNewsRequest(val errorMessage: String) : DataEvent()
}