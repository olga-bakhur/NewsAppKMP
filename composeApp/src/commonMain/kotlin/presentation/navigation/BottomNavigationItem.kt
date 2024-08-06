package presentation.navigation

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

sealed class BottomNavigationItem(
    val textId: StringResource,
    val unSelectedIcon: ImageVector = Icons.Outlined.Home,
    val selectedIcon: ImageVector = Icons.Filled.Home,
    val route: String = EMPTY
) {
    data object Feed : BottomNavigationItem(
        textId = Res.string.feed,
        unSelectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        route = Screen.Feed.route
    )

    data object Favorite : BottomNavigationItem(
        textId = Res.string.favorite,
        unSelectedIcon = Icons.Outlined.Favorite,
        selectedIcon = Icons.Filled.Favorite,
        route = Screen.Favorite.route
    )

    data object Settings : BottomNavigationItem(
        textId = Res.string.settings,
        unSelectedIcon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings,
        route = Screen.Settings.route
    )
}