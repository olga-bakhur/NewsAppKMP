package presentation.navigation.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import common.EMPTY
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.favorite
import newsappkmp.composeapp.generated.resources.feed
import newsappkmp.composeapp.generated.resources.settings
import org.jetbrains.compose.resources.StringResource
import presentation.navigation.Screen

sealed class NavigationItem(
    val textId: StringResource,
    val unselectedIcon: ImageVector = Icons.Outlined.Home,
    val selectedIcon: ImageVector = Icons.Filled.Home,
    val route: String = EMPTY
) {
    data object Feed : NavigationItem(
        textId = Res.string.feed,
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        route = Screen.Feed.route
    )

    data object Favorite : NavigationItem(
        textId = Res.string.favorite,
        unselectedIcon = Icons.Outlined.Favorite,
        selectedIcon = Icons.Filled.Favorite,
        route = Screen.Favorite.route
    )

    data object Settings : NavigationItem(
        textId = Res.string.settings,
        unselectedIcon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings,
        route = Screen.Settings.route
    )
}