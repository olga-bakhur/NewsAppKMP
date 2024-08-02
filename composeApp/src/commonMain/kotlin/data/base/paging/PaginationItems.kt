package data.base.paging

data class PaginationItems<T>(
    val items: List<T>,
    val pageRequested: Int,
    val pageSize: Int,
    val totalPagesCount: Int,
    val totalItemsCount: Int,
    val startIndex: Int,
    val orderBy: String,
)