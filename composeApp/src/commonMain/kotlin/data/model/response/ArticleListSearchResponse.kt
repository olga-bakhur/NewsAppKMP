package data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ArticleListSearchResponse(
    val response: ArticleListResponse
)

@Serializable
data class ArticleListResponse(
    val status: String,
    val userTier: String,
    val total: Int,
    val startIndex: Int,
    val pageSize: Int,
    val currentPage: Int,
    val pages: Int,
    val orderBy: String,
    val results: List<ArticleResponse>
)