package data.model.dto

import kotlinx.serialization.Serializable


@Serializable
data class Section(
    val sectionId: String,
    val sectionName: String
)