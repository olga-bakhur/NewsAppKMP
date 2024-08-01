package data.repository

import data.base.error.handleError
import data.base.result.ApiSuccess
import data.base.result.Result
import data.base.result.map
import data.model.dto.Article
import data.source.remote.ArticlesApi
import data.util.toArticle
import data.util.toArticleList
import domain.repository.ArticlesRepository

internal class ArticlesRepositoryImpl(
    private val articlesApi: ArticlesApi
) : ArticlesRepository {
    override suspend fun fetchArticleList(): Result<List<Article>> {
        val result = articlesApi.fetchArticleList()
            .map {
                it.toArticleList()
            }

        return when (result) {
            is ApiSuccess -> {
                Result.Success(result.data)
            }

            else -> handleError(result)
        }
    }

    override suspend fun fetchArticleDetailById(articleId: String): Result<Article> {
        val result = articlesApi.fetchArticleDetailById(articleId = articleId)
            .map {
                it.toArticle()
            }

        return when (result) {
            is ApiSuccess -> {
                Result.Success(result.data)
            }

            else -> handleError(result)
        }
    }
}