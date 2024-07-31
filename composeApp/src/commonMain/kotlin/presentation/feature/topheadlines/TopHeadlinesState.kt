package presentation.feature.topheadlines

import data.base.error.AppError
import data.model.dto.Article

data class TopHeadlinesState(
    val loading: Boolean = false,
    val error: AppError? = null,
    val articles: List<Article>? = null
)