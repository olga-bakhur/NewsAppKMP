package domain.usecase

import data.base.result.Result
import data.model.dto.Section
import domain.repository.SectionsRepository

class FetchSectionsUseCase(
    private val sectionsRepository: SectionsRepository
) {

    suspend fun fetchSections(): Result<List<Section>> =
        sectionsRepository.fetchSections()
}