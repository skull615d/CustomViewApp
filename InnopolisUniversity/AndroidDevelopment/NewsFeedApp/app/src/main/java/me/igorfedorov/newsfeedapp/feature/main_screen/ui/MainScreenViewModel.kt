package me.igorfedorov.newsfeedapp.feature.main_screen.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.igorfedorov.newsfeedapp.common.Either
import me.igorfedorov.newsfeedapp.common.exception.Failure
import me.igorfedorov.newsfeedapp.common.onFailure
import me.igorfedorov.newsfeedapp.common.onSuccess
import me.igorfedorov.newsfeedapp.feature.main_screen.domain.model.Article
import me.igorfedorov.newsfeedapp.feature.main_screen.domain.use_case.get_last_hour_news_use_case.GetLastHourNewsUseCase

class MainScreenViewModel(
    private val getLastHourNewsUseCase: GetLastHourNewsUseCase
) : ViewModel() {

    private val _articles: MutableLiveData<List<Article>> = MutableLiveData()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    init {
        loadNews()
    }

    fun loadNews() =
        viewModelScope.launch {
            try {
                Either.Right(getLastHourNewsUseCase())
            } catch (t: Throwable) {
                Either.Left(Failure.ServerError)
            }.apply {
                onFailure(::handleFailure)
                onSuccess(::handleArticleList)
            }
        }

    private fun handleArticleList(articles: List<Article>) {
        _articles.postValue(articles)
    }

    private fun handleFailure(failure: Failure) {
        _failure.value = failure
    }

}