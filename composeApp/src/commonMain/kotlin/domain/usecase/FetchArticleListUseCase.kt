package domain.usecase

import data.base.result.Result
import data.model.dto.Article
import domain.repository.ArticlesRepository

class FetchArticleListUseCase(
    private val articlesRepository: ArticlesRepository
) {

    suspend fun fetchArticleList(): Result<List<Article>> = articlesRepository.fetchArticleList()
}