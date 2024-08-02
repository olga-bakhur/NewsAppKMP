package data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleListSearchResponse(
    val response: ArticleListResponse
)

@Serializable
data class ArticleListResponse(
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