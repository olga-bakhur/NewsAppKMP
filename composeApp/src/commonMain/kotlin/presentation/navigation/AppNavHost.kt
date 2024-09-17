package presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
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
import presentation.feature.profile.ProfileScreen
import presentation.feature.settings.SettingsScreen

private const val animationDuration = 400

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier,
        ) {
            navigationBarGraph(
                navController = navController,
                modifier = Modifier.padding(paddingValues),
                sharedTransitionScope = this@SharedTransitionLayout
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.navigationBarGraph(
    navController: NavController,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope
) {
    navigation(
        startDestination = Screen.Feed.route,
        route = Graph.NavigationBar.route
    ) {
        /* tabs */
        composable(
            route = Screen.Feed.route,
            popEnterTransition = { enterAnimation() },
            popExitTransition = { exitAnimation() }
        ) {
            FeedScreen(
                viewModel = koinViewModel(),
                onArticleClicked = { articleId ->
                    navController.navigate(
                        route = Screen.ArticleDetail.createRoute(articleId)
                    )
                },
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = this@composable
            )
        }

        composable(
            route = Screen.Favorite.route,
            enterTransition = { enterAnimation() },
            exitTransition = { exitAnimation() },
            popEnterTransition = { enterAnimation() },
            popExitTransition = { exitAnimation() }
        ) {
            FavoriteScreen(
                viewModel = koinViewModel(),
                onBackClicked = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.Profile.route,
            enterTransition = { enterAnimation() },
            exitTransition = { exitAnimation() },
            popEnterTransition = { enterAnimation() },
            popExitTransition = { exitAnimation() }
        ) {
            ProfileScreen(
                viewModel = koinViewModel(),
                onBackClicked = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.Settings.route,
            enterTransition = { enterAnimation() },
            exitTransition = { exitAnimation() },
            popEnterTransition = { enterAnimation() },
            popExitTransition = { exitAnimation() }
        ) {
            SettingsScreen(
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
            ),
        ) { backStackEntry ->
            ArticleDetailScreen(
                viewModel = koinViewModel(),
                articleId = backStackEntry.arguments?.getString(articleId) ?: EMPTY,
                onBackClicked = { navController.popBackStack() },
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = this@composable
            )
        }
    }
}

fun AnimatedContentTransitionScope<NavBackStackEntry>.enterAnimation(): EnterTransition =
    fadeIn(
        animationSpec = tween(
            durationMillis = animationDuration,
            easing = LinearEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(
            durationMillis = animationDuration,
            easing = EaseIn
        ),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.exitAnimation(): ExitTransition =
    fadeOut(
        animationSpec = tween(
            durationMillis = animationDuration,
            easing = LinearEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(
            durationMillis = animationDuration,
            easing = EaseOut
        ),
        towards = AnimatedContentTransitionScope.SlideDirection.End
    )