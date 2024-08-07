package data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class Feed(
    val totalArticlesCount: Int,
    val startIndex: Int,
    val pageSize: Int,
    val currentPage: Int,
    val totalPagesCount: Int,
    val orderBy: String,
    val articles: List<Article>
)