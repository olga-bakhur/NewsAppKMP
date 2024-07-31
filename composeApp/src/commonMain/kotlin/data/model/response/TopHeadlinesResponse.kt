package data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TopHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleResponse>?
)