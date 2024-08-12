package data.source.remote.paging

import data.base.result.Result
import data.model.dto.Article
import data.model.dto.Feed
import domain.repository.ArticlesRepository

class ArticlesPagingSource(
    private val articlesRepository: ArticlesRepository,
) : BasePagingSource<Article>() {
    private var sectionId: String? = null
    private var fromDate: String? = null
    private var toDate: String? = null

    fun initFilter(
        sectionId: String?,
        fromDate: String?,
        toDate: String?
    ) {
        this.sectionId = sectionId
        this.fromDate = fromDate
        this.toDate = toDate
    }

    override suspend fun fetchData(page: Int, limit: Int): Result<PaginationItems<Article>> =
        articlesRepository
            .fetchFeed(
                sectionId = sectionId,
                fromDate = fromDate,
                toDate = toDate,
                page = page,
                pageSize = limit
            )
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
