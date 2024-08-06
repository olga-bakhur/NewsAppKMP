package presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    when (navBackStackEntry?.destination?.route) {
        Screen.Feed.route,
        Screen.Favorite.route,
        Screen.Settings.route -> bottomBarState.value = true

        else -> bottomBarState.value = false
    }
    val bottomNavigationItems: List<BottomNavigationItem> = listOf(
        BottomNavigationItem.Feed,
        BottomNavigationItem.Favorite,
        BottomNavigationItem.Settings
    )
    AnimatedVisibility(
        modifier = modifier,
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            NavigationBar {
                bottomNavigationItems
                    .forEach { navigationItem ->
                        val isSelected = currentDestination?.hierarchy
                            ?.any {
                                it.route == navigationItem.route
                            } == true

                        NavigationBarItem(
                            selected = isSelected,
                            label = {
                                Text(stringResource(navigationItem.textId))
                            },
                            icon = {
                                Icon(
                                    imageVector = if (isSelected) {
                                        navigationItem.selectedIcon
                                    } else {
                                        navigationItem.unSelectedIcon
                                    },
                                    contentDescription = stringResource(navigationItem.textId)
                                )
                            },
                            onClick = {
                                navController.navigate(navigationItem.route) {
                                    popUpTo(
                                        route = navController.graph.findStartDestination().route!!
                                    ) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
            }
        }
    )
}