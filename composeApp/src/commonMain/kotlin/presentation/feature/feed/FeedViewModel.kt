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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FeedViewModel(
    private val appDispatchers: AppDispatchers,
    private val fetchFeedUseCase: FetchFeedUseCase,
    private val fetchSectionsUseCase: FetchSectionsUseCase
) : BaseViewModel() {

    override val _loading = MutableStateFlow(false)
    override val _errors = MutableStateFlow<List<AppError>>(emptyList())

    private val _articles = MutableStateFlow(emptyFlow<PagingData<Article>>())
    private val _sections = MutableStateFlow(emptyList<Section>())
    private val _feedFilter: MutableStateFlow<FeedFilter> = MutableStateFlow(FeedFilter())

    val state: StateFlow<FeedState> = combine(
        _loading,
        _errors,
        _sections,
        _feedFilter,
        _articles
    ) { loading, errors, sections, feedFilter, articles ->
        FeedState(
            loading = loading,
            errors = errors,
            sections = sections,
            feedFilter = feedFilter,
            articles = articles
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        FeedState()
    )

    fun fetchSections() {
        viewModelScope.launch(appDispatchers.io) {
            when (val result = fetchSectionsUseCase.fetchSections()) {
                is Result.Success -> _sections.emit(result.data)
                is Result.Error -> addError(result.error)
            }
        }
    }

    fun setFilterBySection(sectionId: String?) {
        viewModelScope.launch(appDispatchers.io) {
            val currentFilters = _feedFilter.value
            val newSectionFilter = if (currentFilters.sectionId == sectionId) null else sectionId

            _feedFilter.emit(
                currentFilters.copy(
                    sectionId = newSectionFilter,
                    fromDate = currentFilters.fromDate,
                    toDate = currentFilters.toDate
                )
            )

            getPaginatedArticlesList()
        }
    }

    fun getPaginatedArticlesList() {
        viewModelScope.launch(appDispatchers.io) {
            val currentFilters = _feedFilter.value

            _articles.emit(
                fetchFeedUseCase.getPaginatedArticlesList(
                    sectionId = currentFilters.sectionId,
                    fromDate = currentFilters.fromDate,
                    toDate = currentFilters.sectionId,
                )
            )
        }
    }
}