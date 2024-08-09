package presentation.feature.articledetail

import data.base.error.AppError
import data.model.dto.Article

data class ArticleDetailState(
    val loading: Boolean = false,
    val errors: List<AppError> = emptyList(),
    val article: Article? = null
)