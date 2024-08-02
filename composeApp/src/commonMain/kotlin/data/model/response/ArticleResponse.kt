package data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("id")
    val articleId: String,
    val type: String,
    @SerialName("sectionId")
    val categoryId: String,
    @SerialName("sectionName")
    val category: String,
    @SerialName("webPublicationDate")
    val publicationDate: String,
    @SerialName("webTitle")
    val title: String,
    val webUrl: String,
    val apiUrl: String,
    val isHosted: Boolean,
    val pillarId: String? = null,
    val pillarName: String? = null,
    val fields: ArticleResponseFields
)

@Serializable
data class ArticleResponseFields(
    val trailText: String,
    val bodyText: String? = null,
    val byline: String? = null,
    val lastModified: String,
    @SerialName("publication")
    val source: String,
    val thumbnail: String
)