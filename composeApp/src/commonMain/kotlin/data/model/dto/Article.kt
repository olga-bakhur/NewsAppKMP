package data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val sourceId: String?,
    val sourceName: String?,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)