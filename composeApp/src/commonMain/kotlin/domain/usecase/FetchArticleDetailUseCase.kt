package domain.usecase

import data.base.result.Result
import data.model.dto.Article
import domain.repository.ArticlesRepository

class FetchArticleDetailUseCase(
    private val articlesRepository: ArticlesRepository
) {

    suspend fun fetchArticleDetailById(articleId: String): Result<Article> =
        articlesRepository.fetchArticleDetailById(articleId)
}