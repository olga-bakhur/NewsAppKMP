import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import common.EMPTY
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import presentation.feature.articledetail.ArticleDetailScreen
import presentation.feature.topheadlines.TopHeadlinesScreen
import presentation.navigation.Screen
import presentation.navigation.articleUrl

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Screen.TopHeadlines.route
            ) {
                composable(
                    Screen.TopHeadlines.route
                ) {
                    TopHeadlinesScreen(
                        navController = navController
                    )
                }

                composable(
                    route = Screen.ArticleDetail(EMPTY).route,
                    arguments = listOf(
                        navArgument(name = articleUrl) {
                            type = NavType.StringType
                            defaultValue = EMPTY
                        }
                    )
                ) { backStackEntry ->
                    val articleUrl = backStackEntry.arguments?.getString(articleUrl) ?: EMPTY

                    ArticleDetailScreen(
                        navController = navController,
                        articleUrl = articleUrl
                    )
                }
            }
        }
    }
}