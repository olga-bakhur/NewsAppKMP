package presentation.navigation

import net.thauvin.erik.urlencoder.UrlEncoderUtil

const val articleId = "articleId"

sealed class Screen(val route: String) {
    data object Feed : Screen("feed")

    data object ArticleDetail : Screen("article_detail/{$articleId}") {
        fun createRoute(articleId: String): String {
            val encodedArticleId = UrlEncoderUtil.encode(articleId)
            return "article_detail/$encodedArticleId"
        }

        fun decodeUrl(articleId: String): String = UrlEncoderUtil.decode(articleId)
    }

    data object Favorite : Screen("favorite")
    data object Settings : Screen("settings")
}