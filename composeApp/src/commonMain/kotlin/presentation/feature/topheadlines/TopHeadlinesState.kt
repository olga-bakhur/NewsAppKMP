package presentation.feature.topheadlines

import data.base.result.Result
import data.model.dto.Article

data class TopHeadlinesState(
    val resultListArticle: Result<List<Article>> = Result.Success(emptyList())
)