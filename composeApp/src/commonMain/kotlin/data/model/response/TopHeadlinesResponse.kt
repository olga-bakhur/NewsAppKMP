package data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TopHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleResponse>?
)

@Serializable
data class ArticleResponse(
    val source: ArticleSource?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)

@Serializable
data class ArticleSource(
    val id: String?,
    val name: String?
)