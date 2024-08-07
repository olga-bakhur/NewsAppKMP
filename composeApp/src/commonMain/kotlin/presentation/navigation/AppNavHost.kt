package presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import common.EMPTY
import org.koin.compose.viewmodel.koinViewModel
import presentation.feature.articledetail.ArticleDetailScreen
import presentation.feature.favorite.FavoriteScreen
import presentation.feature.feed.FeedScreen
import presentation.feature.settings.SettingsScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        navigationBarGraph(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.navigationBarGraph(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    navigation(
        startDestination = Screen.Feed.route,
        route = Graph.NavigationBar.route
    ) {
        /* tabs */
        composable(Screen.Feed.route) {
            FeedScreen(
                viewModel = koinViewModel(),
                onArticleClicked = { articleId ->
                    navController.navigate(
                        route = Screen.ArticleDetail.createRoute(articleId)
                    )
                }
            )
        }

        composable(Screen.Favorite.route) {
            FavoriteScreen(
                modifier = modifier,
                viewModel = koinViewModel(),
                onBackClicked = { navController.popBackStack() }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                modifier = modifier,
                viewModel = koinViewModel(),
                onBackClicked = { navController.popBackStack() }
            )
        }

        /* other screens */
        composable(
            route = Screen.ArticleDetail.route,
            arguments = listOf(
                navArgument(name = articleId) {
                    type = NavType.StringType
                    defaultValue = EMPTY
                }
            )
        ) { backStackEntry ->
            ArticleDetailScreen(
                viewModel = koinViewModel(),
                articleId = backStackEntry.arguments?.getString(articleId) ?: EMPTY,
                onBackClicked = { navController.popBackStack() }
            )
        }
    }
}