package presentation.navigation.navbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun SideNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val navBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = navBackStackEntry?.destination

    val navigationBarItems: List<NavigationItem> = listOf(
        NavigationItem.Feed,
        NavigationItem.Favorite,
        NavigationItem.Profile,
        NavigationItem.Settings
    )

    AnimatedVisibility(
        modifier = modifier,
        visible = navBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            NavigationRail {
                navigationBarItems
                    .forEach { navigationItem ->
                        val isSelected = currentDestination?.hierarchy
                            ?.any {
                                it.route == navigationItem.route
                            } == true

                        NavigationRailItem(
                            selected = isSelected,
                            label = {
                                NavigationLabel(
                                    navigationItem.textId
                                )
                            },
                            icon = {
                                NavigationIcon(
                                    isSelected = isSelected,
                                    selectedIcon = navigationItem.selectedIcon,
                                    unselectedIcon = navigationItem.unselectedIcon,
                                    contentDescription = navigationItem.textId
                                )
                            },
                            onClick = {
                                onNavigationItemClicked(
                                    navController = navController,
                                    route = navigationItem.route
                                )
                            }
                        )
                    }
            }
        }
    )
}