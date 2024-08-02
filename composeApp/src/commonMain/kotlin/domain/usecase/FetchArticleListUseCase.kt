package domain.usecase

import data.base.result.Result
import data.model.dto.ArticleList
import domain.repository.ArticlesRepository

class FetchArticleListUseCase(
    private val articlesRepository: ArticlesRepository
) {

    suspend fun fetchArticleList(): Result<ArticleList> =
        articlesRepository.fetchArticleList(
            page = 1,
            pageSize = 5
        )
}