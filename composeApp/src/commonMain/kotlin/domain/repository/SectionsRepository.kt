package domain.repository

import data.base.result.Result
import data.model.dto.Section

interface SectionsRepository {

    suspend fun fetchSections(): Result<List<Section>>
}