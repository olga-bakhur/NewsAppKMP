package presentation.feature.feed

import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import data.base.error.AppError
import data.model.dto.Article
import data.model.dto.Section
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Immutable
data class FeedState(
    val loading: Boolean = false,
    val errors: List<AppError> = emptyList(),
    val sections: List<Section> = emptyList(),
    val feedFilter: FeedFilter = FeedFilter(),
    val articles: Flow<PagingData<Article>> = emptyFlow(),
    val saveArticleResult: SaveArticleResult = SaveArticleResult.None
)

@Immutable
data class FeedFilter(
    val sectionId: String? = null,
    val fromDate: Long? = null,
    val toDate: Long? = null
)

@Immutable
sealed interface SaveArticleResult {
    data object Saved : SaveArticleResult
    data object Removed : SaveArticleResult
    data object None : SaveArticleResult
}