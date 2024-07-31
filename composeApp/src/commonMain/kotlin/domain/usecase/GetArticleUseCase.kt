package domain.usecase

import data.base.result.Result
import data.model.dto.Article
import domain.repository.NewsRepository

class GetArticleUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun getArticleByUrl(url: String): Result<Article> = newsRepository.getArticleByUrl(url)
}