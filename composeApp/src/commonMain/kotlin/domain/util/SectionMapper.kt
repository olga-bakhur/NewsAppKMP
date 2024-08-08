package domain.util

import data.model.dto.Section
import data.model.response.section.SectionResponse
import data.model.response.section.SectionsSearchResponse


fun SectionResponse.toSection(): Section = Section(
    sectionId = sectionId,
    sectionName = sectionName
)

fun SectionsSearchResponse.toSectionList(): List<Section> =
    response.sections.map {
        it.toSection()
    }