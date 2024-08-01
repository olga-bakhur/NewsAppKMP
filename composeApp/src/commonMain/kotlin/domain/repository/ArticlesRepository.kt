package domain.repository

import data.base.result.Result
import data.model.dto.Article

interface ArticlesRepository {

    suspend fun fetchArticleList(page: Int, pageSize: Int): Result<List<Article>>

    suspend fun fetchArticleDetailById(articleId: String): Result<Article>
}