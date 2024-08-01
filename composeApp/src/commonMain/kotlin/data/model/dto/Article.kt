package data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: String,
    val type: String,
    val sectionId: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String,
    val apiUrl: String,
    val isHosted: Boolean,
    val pillarId: String,
    val pillarName: String
)