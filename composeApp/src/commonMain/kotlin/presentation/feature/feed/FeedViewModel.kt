package presentation.feature.feed

import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import base.BaseViewModel
import data.base.error.AppError
import data.base.result.Result
import data.model.dto.Article
import data.model.dto.Section
import domain.usecase.FetchFeedUseCase
import domain.usecase.FetchSectionsUseCase
import domain.util.AppDispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FeedViewModel(
    private val appDispatchers: AppDispatchers,
    private val fetchFeedUseCase: FetchFeedUseCase,
    private val fetchSectionsUseCase: FetchSectionsUseCase
) : BaseViewModel() {

    override val loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<AppError?>(null)
    private val _articles = MutableStateFlow(emptyFlow<PagingData<Article>>())
    private val _sections = MutableStateFlow(emptyList<Section>())
    private val _sectionId: MutableStateFlow<String?> = MutableStateFlow(null)

    val state: StateFlow<FeedListState> = combine(
        loading,
        _error,
        _sections,
        _sectionId,
        _articles
    ) { loading, error, sections, sectionId, articles ->
        FeedListState(
            loading = loading,
            error = error,
            sections = sections,
            sectionId = sectionId,
            articles = articles
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        FeedListState()
    )

    fun fetchSections() {
        viewModelScope.launch(appDispatchers.io) {
            when (val result = fetchSectionsUseCase.fetchSections()) {
                is Result.Success -> {
                    println("Result.Success: $result")
                    _sections.emit(result.data)
                }

                is Result.Error -> {
                    println("Result.Error: ${result.error}")
                    _error.emit(result.error)
                }
            }
        }
    }

    fun setFilterBySection(sectionId: String?) {
        viewModelScope.launch(appDispatchers.io) {
            _sectionId.emit(sectionId)

            getPaginatedArticlesList()
        }
    }

    fun getPaginatedArticlesList() {
        viewModelScope.launch(appDispatchers.io) {
            _articles.emit(fetchFeedUseCase.getPaginatedArticlesList(_sectionId.first()))
        }
    }
}