package presentation.navigation.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.ui.graphics.vector.ImageVector
import common.EMPTY
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.screen_title_favorite
import newsappkmp.composeapp.generated.resources.screen_title_feed
import newsappkmp.composeapp.generated.resources.screen_title_settings
import org.jetbrains.compose.resources.StringResource
import presentation.navigation.Screen

sealed class NavigationItem(
    val textId: StringResource,
    val unselectedIcon: ImageVector = Icons.Outlined.Home,
    val selectedIcon: ImageVector = Icons.Filled.Home,
    val route: String = EMPTY
) {
    data object Feed : NavigationItem(
        textId = Res.string.screen_title_feed,
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        route = Screen.Feed.route
    )

    data object Favorite : NavigationItem(
        textId = Res.string.screen_title_favorite,
        unselectedIcon = Icons.Outlined.Star,
        selectedIcon = Icons.Filled.Star,
        route = Screen.Favorite.route
    )

    data object Settings : NavigationItem(
        textId = Res.string.screen_title_settings,
        unselectedIcon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings,
        route = Screen.Settings.route
    )
}