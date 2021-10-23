package me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui

import me.igorfedorov.newsfeedapp.base.base_view_model.BaseViewModel
import me.igorfedorov.newsfeedapp.base.base_view_model.Event
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.NewsFeedInteractor
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

class NewsFeedScreenViewModel(
    private val newsFeedInteractor: NewsFeedInteractor
) : BaseViewModel<ViewState>() {

    init {
        processUiEvent(UIEvent.GetCurrentNews)
    }

    override fun initialViewState(): ViewState {
        return ViewState(
            articleList = emptyList(),
            article = null,
            errorMessage = null,
            toastMessage = null
        )
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.GetCurrentNews -> {
                newsFeedInteractor.getHeadlinesNews().fold(
                    onError = {
                        processDataEvent(DataEvent.ErrorNewsRequest(it.localizedMessage ?: ""))
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessNewsRequest(it))
                    }
                )
            }
            is UIEvent.OnArticleCLick -> {
                return previousState.copy(article = event.article)
            }
            is UIEvent.OnGoBackFromWebView -> {
                return previousState.copy(article = null)
            }
            is UIEvent.ShowToast -> {
                return previousState.copy(
                    toastMessage = event.toastMessage
                )
            }
            is UIEvent.OnConfigurationChanged -> {
                processUiEvent(UIEvent.GetCurrentNews)
                return previousState.copy(
                    toastMessage = null
                )
            }
            is DataEvent.AddArticleToBookmarks -> {
                newsFeedInteractor.addArticleToBookmarks(event.article)
                return previousState.copy(articleList = previousState.articleList.map {
                    if (it == event.article) {
                        it.copy(isBookmarked = true)
                    } else {
                        it
                    }
                })
            }
            is DataEvent.RemoveArticleFromBookmarks -> {
                newsFeedInteractor.deleteArticleFromBookmarks(event.article)
                return previousState.copy(articleList = previousState.articleList.map {
                    if (it == event.article) {
                        it.copy(isBookmarked = false)
                    } else {
                        it
                    }
                })
            }
            is DataEvent.SuccessNewsRequest -> {
                return previousState.copy(
                    articleList = event.articleList,
                    errorMessage = null
                )
            }
            is DataEvent.ErrorNewsRequest -> {
                return previousState.copy(
                    errorMessage = event.errorMessage
                )
            }
        }
        return null
    }

    fun onConfigurationChanged() {
        processUiEvent(UIEvent.OnConfigurationChanged)
    }

    fun onBookmarkClick(article: Article) {
        if (!article.isBookmarked) {
            processDataEvent(DataEvent.AddArticleToBookmarks(article))
            processUiEvent(UIEvent.ShowToast("${article.title} added to bookmarks"))
        } else {
            processDataEvent(DataEvent.RemoveArticleFromBookmarks(article))
        }
    }

    fun openArticleWebView(article: Article) {
        processUiEvent(UIEvent.OnArticleCLick(article))
    }

    fun closeArticleWebView() {
        processUiEvent(UIEvent.OnGoBackFromWebView)
    }
}