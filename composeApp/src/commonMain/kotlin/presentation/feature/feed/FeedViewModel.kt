package presentation.feature.feed

import androidx.lifecycle.viewModelScope
import app.cash.paging.PagingData
import data.base.error.AppError
import data.base.result.Result
import data.model.dto.Article
import data.model.dto.Section
import domain.usecase.FetchFeedUseCase
import domain.usecase.FetchSectionsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import presentation.base.BaseViewModel
import presentation.util.AppDispatchers

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
    private val _saveArticleResult: MutableStateFlow<Flow<Boolean>> = MutableStateFlow(emptyFlow())

    val state: StateFlow<FeedState> = combine(
        _loading,
        _errors,
        _sections,
        _feedFilter,
        _articles
    ) { loading, errors, sections, feedFilter, articles ->
        CombinedResult(loading, errors, sections, feedFilter, articles)
    }
        .combine(_saveArticleResult) { combinedResult, saveArticleResult ->
            FeedState(
                loading = combinedResult.loading,
                errors = combinedResult.errors,
                sections = combinedResult.sections,
                feedFilter = combinedResult.feedFilter,
                articles = combinedResult.articles,
                saveArticleResult = saveArticleResult
            )
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            FeedState()
        )

    data class CombinedResult(
        val loading: Boolean,
        val errors: List<AppError>,
        val sections: List<Section>,
        val feedFilter: FeedFilter,
        val articles: Flow<PagingData<Article>>
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
        }
    }

    fun setFilterByDate(fromDate: Long?, toDate: Long?) {
        viewModelScope.launch(appDispatchers.io) {
            val currentFilters = _feedFilter.value
            val currentFrom = currentFilters.fromDate
            val currentTo = currentFilters.toDate

            if (currentFrom == fromDate && currentTo == toDate) {
                return@launch
            }

            when {
                fromDate != null && toDate == null -> {
                    _feedFilter.emit(
                        currentFilters.copy(
                            sectionId = currentFilters.sectionId,
                            fromDate = fromDate,
                            toDate = fromDate
                        )
                    )
                }

                fromDate == null && toDate != null -> {
                    _feedFilter.emit(
                        currentFilters.copy(
                            sectionId = currentFilters.sectionId,
                            fromDate = toDate,
                            toDate = toDate
                        )
                    )
                }

                else -> {
                    _feedFilter.emit(
                        currentFilters.copy(
                            sectionId = currentFilters.sectionId,
                            fromDate = fromDate,
                            toDate = toDate
                        )
                    )
                }
            }
        }
    }

    fun getPaginatedArticlesList() {
        viewModelScope.launch(appDispatchers.io) {
            val currentFilters = _feedFilter.value

            _articles.emit(
                fetchFeedUseCase.getPaginatedArticlesList(
                    sectionId = currentFilters.sectionId,
                    fromDate = currentFilters.fromDate,
                    toDate = currentFilters.toDate,
                )
            )
        }
    }

    fun saveArticle() {
        viewModelScope.launch(appDispatchers.io) {
            val result = Result.Success(Unit) // TODO: to be implemented

            when (result) {
                is Result.Success -> {
                    _saveArticleResult.emit(flowOf(true))
                    delay(1000)
                    _saveArticleResult.emit(emptyFlow())
                }

                is Result.Error<*> -> addError(result.error)
            }
        }
    }

    fun removeArticle() {
        viewModelScope.launch(appDispatchers.io) {
            val result = Result.Success(Unit) // TODO: to be implemented

            when (result) {
                is Result.Success -> {
                    _saveArticleResult.emit(flowOf(false))
                    delay(1000)
                    _saveArticleResult.emit(emptyFlow())
                }

                is Result.Error<*> -> addError(result.error)
            }
        }
    }
}