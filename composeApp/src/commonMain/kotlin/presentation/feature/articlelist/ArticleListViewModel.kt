package presentation.feature.articlelist

import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import base.BaseViewModel
import common.EMPTY
import data.base.error.AppError
import data.model.dto.Article
import domain.usecase.FetchArticleListUseCase
import domain.util.AppDispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ArticleListViewModel(
    private val fetchArticleListUseCase: FetchArticleListUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {

    override val loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<AppError?>(null)
    private val _articles = MutableStateFlow(emptyFlow<PagingData<Article>>())

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

    fun getPaginatedArticlesList() {
        viewModelScope.launch(appDispatchers.io) {
            _articles.emit(fetchArticleListUseCase.getPaginatedArticlesList(EMPTY))
        }
    }
}