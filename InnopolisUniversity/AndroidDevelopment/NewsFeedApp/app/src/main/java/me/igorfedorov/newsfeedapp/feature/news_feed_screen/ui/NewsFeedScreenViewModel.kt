package me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui

import me.igorfedorov.newsfeedapp.base.base_view_model.BaseViewModel
import me.igorfedorov.newsfeedapp.base.base_view_model.Event
import me.igorfedorov.newsfeedapp.base.utils.SingleLiveEvent
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.NewsFeedInteractor
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article

class NewsFeedScreenViewModel(
    private val newsFeedInteractor: NewsFeedInteractor
) : BaseViewModel<ViewState>() {

    init {
        processUiEvent(UIEvent.GetCurrentNews)
    }

    val toastEvent = SingleLiveEvent<String>()

    override fun initialViewState(): ViewState {
        return ViewState(
            articles = emptyList(),
            article = null,
            errorMessage = null
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
            is UIEvent.OnConfigurationChanged -> {
                processUiEvent(UIEvent.GetCurrentNews)
            }
            is DataEvent.AddArticleToBookmarks -> {
                newsFeedInteractor.addArticleToBookmarks(event.article)
                return previousState.copy(articles = previousState.articles.map {
                    if (it == event.article) {
                        it.copy(isBookmarked = true)
                    } else {
                        it
                    }
                })
            }
            is DataEvent.RemoveArticleFromBookmarks -> {
                newsFeedInteractor.deleteArticleFromBookmarks(event.article)
                return previousState.copy(articles = previousState.articles.map {
                    if (it == event.article) {
                        it.copy(isBookmarked = false)
                    } else {
                        it
                    }
                })
            }
            is DataEvent.SuccessNewsRequest -> {
                return previousState.copy(
                    articles = event.articleList,
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
            toastEvent.postValue("${article.title} added to bookmarks")
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