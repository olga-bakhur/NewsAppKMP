package presentation.feature.feed

import androidx.paging.PagingData
import data.base.error.AppError
import data.model.dto.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FeedListState(
    val loading: Boolean = false,
    val error: AppError? = null,
    val articles: Flow<PagingData<Article>> = emptyFlow()
)