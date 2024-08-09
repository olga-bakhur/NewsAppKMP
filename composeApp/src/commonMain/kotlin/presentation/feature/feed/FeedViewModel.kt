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
    override val errors = MutableStateFlow<List<AppError>>(emptyList())

    private val _articles = MutableStateFlow(emptyFlow<PagingData<Article>>())
    private val _sections = MutableStateFlow(emptyList<Section>())
    private val _sectionId: MutableStateFlow<String?> = MutableStateFlow(null)

    val state: StateFlow<FeedState> = combine(
        loading,
        errors,
        _sections,
        _sectionId,
        _articles
    ) { loading, errors, sections, sectionId, articles ->
        FeedState(
            loading = loading,
            errors = errors,
            sections = sections,
            sectionId = sectionId,
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
            val newFilter = if (_sectionId.value == sectionId) null else sectionId
            _sectionId.emit(newFilter)

            getPaginatedArticlesList()
        }
    }

    fun getPaginatedArticlesList() {
        viewModelScope.launch(appDispatchers.io) {
            _articles.emit(fetchFeedUseCase.getPaginatedArticlesList(_sectionId.first()))
        }
    }
}