package domain.repository

import data.base.result.Result
import data.model.dto.Article

interface NewsRepository {

    suspend fun getTopHeadlines(): Result<List<Article>>
}