package presentation.feature.articledetail

import androidx.compose.runtime.Immutable
import data.base.error.AppError
import data.model.dto.Article

@Immutable
data class ArticleDetailState(
    val loading: Boolean = false,
    val errors: List<AppError> = emptyList(),
    val article: Article? = null
)