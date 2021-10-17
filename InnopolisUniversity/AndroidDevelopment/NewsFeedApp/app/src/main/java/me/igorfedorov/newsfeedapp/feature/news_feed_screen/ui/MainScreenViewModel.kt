package me.igorfedorov.newsfeedapp.feature.news_feed_screen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.igorfedorov.newsfeedapp.common.exception.CustomError
import me.igorfedorov.newsfeedapp.common.onFailure
import me.igorfedorov.newsfeedapp.common.onSuccess
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.model.Article
import me.igorfedorov.newsfeedapp.feature.news_feed_screen.domain.use_case.get_last_hour_news_use_case.GetLastHourNewsUseCase

class MainScreenViewModel(
    private val getLastHourNewsUseCase: GetLastHourNewsUseCase
) : ViewModel() {

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _failure: MutableLiveData<CustomError> = MutableLiveData()
    val failure: LiveData<CustomError> = _failure

    private val _isFetching: MutableLiveData<Boolean> = MutableLiveData(false)
    val isFetching: LiveData<Boolean>
        get() = _isFetching

    init {
        loadNews()
    }

    fun loadNews() =
        viewModelScope.launch {
            _isFetching.postValue(true)
            getLastHourNewsUseCase().apply {
                onFailure(::handleFailure)
                onSuccess(::handleArticleList)
            }
        }

    private fun handleArticleList(articles: List<Article>) {
        _articles.postValue(articles)
        _isFetching.postValue(false)
    }

    private fun handleFailure(customError: CustomError) {
        _failure.value = customError
        _isFetching.postValue(false)
    }

}