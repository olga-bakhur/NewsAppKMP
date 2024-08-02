package domain.repository

import data.base.result.Result
import data.model.dto.Article
import data.model.dto.ArticleList

interface ArticlesRepository {

    suspend fun fetchArticleList(page: Int, pageSize: Int): Result<ArticleList>

    suspend fun fetchArticleDetailById(articleId: String): Result<Article>
}