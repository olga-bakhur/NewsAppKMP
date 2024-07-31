package data.repository

import data.base.error.handleError
import data.base.result.ApiSuccess
import data.base.result.Result
import data.base.result.map
import data.model.dto.Article
import data.source.remote.NewsApi
import data.util.toArticle
import data.util.toArticleList
import domain.repository.NewsRepository

internal class NewsRepositoryImpl(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlines(): Result<List<Article>> {
        val result = newsApi.getTopHeadlines()
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

    override suspend fun getArticleByUrl(url: String): Result<Article> {
        val result = newsApi.getArticleByUrl(url)
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