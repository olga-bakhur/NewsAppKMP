package data.repository

import data.base.error.handleError
import data.base.result.ApiSuccess
import data.base.result.Result
import data.base.result.map
import data.model.dto.Section
import data.source.remote.ArticlesApi
import domain.repository.SectionsRepository
import domain.util.toSectionList

class SectionsRepositoryImpl(
    private val articlesApi: ArticlesApi
) : SectionsRepository {

    override suspend fun fetchSections(): Result<List<Section>> {
        val result = articlesApi.fetchSections()
            .map {
                it.toSectionList()
            }

        return when (result) {
            is ApiSuccess -> Result.Success(result.data)
            else -> handleError(result)
        }
    }
}