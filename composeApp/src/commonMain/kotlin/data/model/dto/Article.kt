package data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val articleId: String,
    val type: String,
    val sectionId: String,
    val category: String,
    val publicationDate: Long,
    val title: String,
    val webUrl: String,
    val apiUrl: String,
    val isHosted: Boolean,
    val pillarId: String,
    val pillarName: String,
    val trailText: String,
    val bodyText: String?,
    val byline: String?,
    val lastModified: Long,
    val source: String,
    val thumbnail: String
)