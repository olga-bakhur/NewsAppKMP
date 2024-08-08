package data.model.response.section

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionResponse(
    @SerialName("id")
    val sectionId: String,
    @SerialName("webTitle")
    val sectionName: String,
    val webUrl: String,
    val apiUrl: String,
    val editions: List<SectionEditionResponse>
)

@Serializable
data class SectionEditionResponse(
    @SerialName("id")
    val editionId: String,
    @SerialName("webTitle")
    val editionTitle: String,
    val webUrl: String,
    val apiUrl: String,
    @SerialName("code")
    val editionCode: String
)