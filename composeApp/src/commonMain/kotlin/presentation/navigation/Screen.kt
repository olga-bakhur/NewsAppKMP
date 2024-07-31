package presentation.navigation

import net.thauvin.erik.urlencoder.UrlEncoderUtil

const val articleUrl = "articleUrl"

sealed class Screen(val route: String) {
    data object TopHeadlines : Screen("top_headlines")

    data object ArticleDetail : Screen("article_detail/{$articleUrl}") {
        fun createRoute(articleUrl: String): String {
            val encodedUrl = UrlEncoderUtil.encode(articleUrl)
            return "article_detail/$encodedUrl"
        }

        fun decodeUrl(articleUrl: String): String = UrlEncoderUtil.decode(articleUrl)
    }
}