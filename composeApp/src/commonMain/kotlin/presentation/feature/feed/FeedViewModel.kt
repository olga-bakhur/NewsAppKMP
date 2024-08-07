package presentation.feature.feed

import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import base.BaseViewModel
import common.EMPTY
import data.base.error.AppError
import data.model.dto.Article
import domain.usecase.FetchFeedUseCase
import domain.util.AppDispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FeedViewModel(
    private val fetchFeedUseCase: FetchFeedUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {

    override val loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<AppError?>(null)
    private val _articles = MutableStateFlow(emptyFlow<PagingData<Article>>())

    val state: StateFlow<FeedListState> = combine(
        loading,
        _error,
        _articles
    ) { loading, error, articles ->
        FeedListState(
            loading = loading,
            error = error,
            articles = articles
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        FeedListState()
    )

    fun getPaginatedArticlesList() {
        viewModelScope.launch(appDispatchers.io) {
            _articles.emit(fetchFeedUseCase.getPaginatedArticlesList(EMPTY))
        }
    }
}