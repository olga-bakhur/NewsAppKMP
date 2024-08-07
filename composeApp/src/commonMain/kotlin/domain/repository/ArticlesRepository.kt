package domain.repository

import data.base.result.Result
import data.model.dto.Article
import data.model.dto.Feed

interface ArticlesRepository {

    suspend fun fetchFeed(page: Int, pageSize: Int): Result<Feed>

    suspend fun fetchArticleDetailById(articleId: String): Result<Article>
}