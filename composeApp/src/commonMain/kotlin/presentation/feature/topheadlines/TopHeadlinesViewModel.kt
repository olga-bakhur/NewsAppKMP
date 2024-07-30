package presentation.feature.topheadlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.base.result.Result
import data.model.dto.Article
import domain.usecase.GetTopHeadlinesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TopHeadlinesViewModel(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    private val resultListArticle =
        MutableStateFlow<Result<List<Article>>>(Result.Success(emptyList()))

    val state: StateFlow<TopHeadlinesState> = resultListArticle.map { result ->
        TopHeadlinesState(
            resultListArticle = result
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TopHeadlinesState())


    fun getTopHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            resultListArticle.emit(
                getTopHeadlinesUseCase.getTopHeadlines()
            )
        }
    }
}