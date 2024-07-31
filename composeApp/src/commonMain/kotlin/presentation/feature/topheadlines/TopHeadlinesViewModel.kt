package presentation.feature.topheadlines

import androidx.lifecycle.viewModelScope
import base.BaseViewModel
import data.base.error.AppError
import data.base.result.Result
import data.model.dto.Article
import domain.usecase.GetTopHeadlinesUseCase
import domain.util.AppDispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class TopHeadlinesViewModel(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {

    override val loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<AppError?>(null)
    private val _articles = MutableStateFlow<List<Article>?>(null)

    val state: StateFlow<TopHeadlinesState> = combine(
        loading,
        _error,
        _articles
    ) { loading, error, articles ->
        TopHeadlinesState(
            loading = loading,
            error = error,
            articles = articles
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        TopHeadlinesState()
    )

    fun fetchHeadlines() {
        launchWithLoading(appDispatchers.io) {
            when (val result = getTopHeadlinesUseCase.getTopHeadlines()) {
                is Result.Success -> _articles.emit(result.data)
                is Result.Error -> _error.emit(result.error)
            }
        }
    }
}