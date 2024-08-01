package presentation.feature.articledetail

import androidx.lifecycle.viewModelScope
import base.BaseViewModel
import data.base.error.AppError
import data.base.result.Result
import data.model.dto.Article
import domain.usecase.FetchArticleDetailUseCase
import domain.util.AppDispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ArticleDetailViewModel(
    private val fetchArticleDetailUseCase: FetchArticleDetailUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {

    override val loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<AppError?>(null)
    private val _article = MutableStateFlow<Article?>(null)

    val state: StateFlow<ArticleDetailState> = combine(
        loading,
        _error,
        _article
    ) { loading, error, article ->
        ArticleDetailState(
            loading = loading,
            error = error,
            article = article
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        ArticleDetailState()
    )

    fun fetchArticleDetailById(articleId: String) {
        launchWithLoading(appDispatchers.io) {
            when (val result = fetchArticleDetailUseCase.fetchArticleDetailById(articleId)) {
                is Result.Success -> _article.emit(result.data)
                is Result.Error -> _error.emit(result.error)
            }
        }
    }
}