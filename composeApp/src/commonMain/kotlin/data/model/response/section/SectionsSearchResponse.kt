package data.model.response.section

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SectionsSearchResponse(
    val response: SectionsResponse
)

@Serializable
data class SectionsResponse(
    val status: String,
    val userTier: String,
    @SerialName("total")
    val totalSectionsCount: Int,
    @SerialName("results")
    val sections: List<SectionResponse>
)