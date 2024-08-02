package domain.usecase

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import data.model.dto.Article
import data.source.paging.ArticlesPagingSource
import kotlinx.coroutines.flow.Flow

class FetchArticleListUseCase(
    private val articlesPagingSource: ArticlesPagingSource
) {

    fun getPaginatedArticlesList(filterId: String): Flow<PagingData<Article>> {
        articlesPagingSource.initFilter(filterId)

        return Pager(
            config = PagingConfig(
                pageSize = 2, // TODO: test
                initialLoadSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { articlesPagingSource }
        ).flow
    }
}