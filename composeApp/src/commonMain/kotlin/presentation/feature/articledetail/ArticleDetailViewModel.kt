package presentation.feature.articledetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.GetTopHeadlinesUseCase
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    fun getArticleDetail() {
        viewModelScope.launch {
            // TODO
        }
    }
}