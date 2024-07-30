package presentation.navigation

const val articleUrl = "articleUrl"

sealed class Screen(val route: String) {
    data object TopHeadlines : Screen("top_headlines")
    data object ArticleDetail : Screen("article_detail/{$articleUrl}")
}