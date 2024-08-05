package presentation.feature.articlelist

import androidx.paging.PagingData
import data.base.error.AppError
import data.model.dto.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class ArticleListState(
    val loading: Boolean = false,
    val error: AppError? = null,
    val articles: Flow<PagingData<Article>> = emptyFlow()
)