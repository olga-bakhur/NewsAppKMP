package data.source.remote.paging

import data.base.result.Result
import data.model.dto.Article
import data.model.dto.Feed
import domain.repository.ArticlesRepository
import kotlin.properties.Delegates

class ArticlesPagingSource(
    private val articlesRepository: ArticlesRepository,
) : BasePagingSource<Article>() {
    private var filterId by Delegates.notNull<String>()

    fun initFilter(id: String) {
        filterId = id
    }

    override suspend fun fetchData(page: Int, limit: Int): Result<PaginationItems<Article>> =
        articlesRepository
            .fetchFeed(page, limit)
            .toPaginationItemsResult()

}

fun Result<Feed>.toPaginationItemsResult(): Result<PaginationItems<Article>> =
    when (this) {
        is Result.Success -> Result.Success(
            with(this.data) {
                PaginationItems(
                    items = articles,
                    pageRequested = currentPage,
                    pageSize = pageSize,
                    totalPagesCount = totalPagesCount,
                    totalItemsCount = totalArticlesCount,
                    startIndex = startIndex,
                    orderBy = orderBy
                )
            }
        )

        is Result.Error -> Result.Error(this.error)
    }
