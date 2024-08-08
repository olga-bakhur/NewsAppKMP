package presentation.feature.feed

import androidx.paging.PagingData
import data.base.error.AppError
import data.model.dto.Article
import data.model.dto.Section
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FeedListState(
    val loading: Boolean = false,
    val error: AppError? = null,
    val sections: List<Section> = emptyList(),
    val articles: Flow<PagingData<Article>> = emptyFlow()
)