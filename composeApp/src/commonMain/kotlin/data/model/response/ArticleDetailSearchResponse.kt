package data.model.response

import kotlinx.serialization.SerialName
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
    @SerialName("content")
    val article: ArticleResponse
)