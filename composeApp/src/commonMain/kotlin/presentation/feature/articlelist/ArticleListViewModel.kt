package presentation.feature.articlelist

import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import base.BaseViewModel
import common.EMPTY
import data.base.error.AppError
import data.model.dto.Article
import domain.usecase.FetchArticleListUseCase
import domain.util.AppDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ArticleListViewModel(
    private val fetchArticleListUseCase: FetchArticleListUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {

    override val loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<AppError?>(null)
    private val _articles = MutableStateFlow<List<Article>?>(null)

    val state: StateFlow<ArticleListState> = combine(
        loading,
        _error,
        _articles
    ) { loading, error, articles ->
        ArticleListState(
            loading = loading,
            error = error,
            articles = articles
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        ArticleListState()
    )

    fun fetchArticleList() {
//        launchWithLoading(appDispatchers.io) {
//            when (val result = fetchArticleListUseCase.fetchArticleList()) {
//                is Result.Success -> _articles.emit(result.data.articles)
//                is Result.Error -> _error.emit(result.error)
//            }
//        }
    }

    // TODO: handle error
    fun getPaginatedArticlesList() {
        val flow: Flow<PagingData<Article>> =
            fetchArticleListUseCase.getPaginatedArticlesList(EMPTY)

        // TODO: update UI state
    }
}