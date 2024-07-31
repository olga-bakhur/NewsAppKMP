package presentation.feature.articledetail

import data.base.error.AppError
import data.model.dto.Article

data class ArticleDetailState(
    val loading: Boolean = false,
    val error: AppError? = null,
    val article: Article? = null
)