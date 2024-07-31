package domain.usecase

import data.base.result.Result
import data.model.dto.Article
import domain.repository.NewsRepository

class GetTopHeadlinesUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun getTopHeadlines(): Result<List<Article>> = newsRepository.getTopHeadlines()
}