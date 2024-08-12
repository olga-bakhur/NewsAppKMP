package domain.usecase

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import data.model.dto.Article
import data.source.remote.paging.ArticlesPagingSource
import kotlinx.coroutines.flow.Flow

class FetchFeedUseCase(
    private val articlesPagingSource: ArticlesPagingSource
) {

    fun getPaginatedArticlesList(
        sectionId: String?,
        fromDate: String?,
        toDate: String?
    ): Flow<PagingData<Article>> {
        articlesPagingSource.initFilter(
            sectionId = sectionId,
            fromDate = fromDate,
            toDate = toDate
        )

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
                prefetchDistance = 3,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { articlesPagingSource }
        ).flow
    }
}