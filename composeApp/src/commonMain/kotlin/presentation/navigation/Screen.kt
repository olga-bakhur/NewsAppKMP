package presentation.navigation

import common.EMPTY

const val articleUrl = "articleUrl"

sealed class Screen(val route: String) {
    data object TopHeadlines : Screen("top_headlines")
    data class ArticleDetail(val articleUrl: String = EMPTY) : Screen("article_detail/{$articleUrl}")
}