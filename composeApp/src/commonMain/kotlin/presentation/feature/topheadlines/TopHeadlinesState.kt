package presentation.feature.topheadlines

import data.base.error.AppError
import data.model.dto.Article

data class TopHeadlinesState(
    val loading: Boolean = false,
    val articles: List<Article>? = null,
    val error: AppError? = null
)