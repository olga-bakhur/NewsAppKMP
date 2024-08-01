package data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDetailSearchResponse(
    val response: SingleArticleResponse
)

@Serializable
data class SingleArticleResponse(
    val status: String,
    val userTier: String,
    val total: Int,
    val content: ArticleResponse
)