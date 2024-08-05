package data.source.remote.paging

import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import data.base.result.Result


abstract class BasePagingSource<Value : Any> : PagingSource<Int, Value>() {

    protected abstract suspend fun fetchData(page: Int, limit: Int): Result<PaginationItems<Value>>

    override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, Value> {
        val currentPage = params.key ?: 1
        val limit = params.loadSize

        return try {
            when (val result: Result<PaginationItems<Value>> = fetchData(currentPage, limit)) {
                is Result.Success -> {
                    PagingSourceLoadResultPage(
                        data = result.data.items,
                        prevKey = if (currentPage == 1) null else currentPage - 1,
                        nextKey = (currentPage + 1).takeIf { result.data.items.lastIndex < result.data.totalItemsCount }
                    )
                }

                is Result.Error -> {
                    PagingSourceLoadResultError(
                        PaginationException("Pagination error!") // TODO: specify
                    )
                }
            }

        } catch (e: Exception) {
            PagingSourceLoadResultError(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}