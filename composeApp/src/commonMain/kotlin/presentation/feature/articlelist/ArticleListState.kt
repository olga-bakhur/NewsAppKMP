package presentation.feature.articlelist

import data.base.error.AppError
import data.model.dto.Article

data class ArticleListState(
    val loading: Boolean = false,
    val error: AppError? = null,
    val articles: List<Article>? = null
)