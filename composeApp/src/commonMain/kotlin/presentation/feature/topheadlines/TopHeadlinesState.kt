package presentation.feature.topheadlines

import data.model.dto.Article

data class TopHeadlinesState(
    val loading: Boolean = false,
    val articles: List<Article>? = null,
)