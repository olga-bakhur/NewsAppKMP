package data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedSearchResponse(
    val response: FeedResponse
)

@Serializable
data class FeedResponse(
    val status: String,
    val userTier: String,
    @SerialName("total")
    val totalArticlesCount: Int,
    val startIndex: Int,
    val pageSize: Int,
    val currentPage: Int,
    @SerialName("pages")
    val totalPagesCount: Int,
    val orderBy: String,
    @SerialName("results")
    val articles: List<ArticleResponse>
)