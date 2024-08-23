package presentation.navigation.navbar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun NavigationLabel(resource: StringResource) {
    Text(
        text = stringResource(resource),
        fontFamily = FontFamily.Cursive,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun NavigationIcon(
    isSelected: Boolean,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector,
    contentDescription: StringResource
) {
    Icon(
        imageVector = if (isSelected) selectedIcon else unselectedIcon,
        contentDescription = stringResource(contentDescription)
    )
}

fun onNavigationItemClicked(
    navController: NavController,
    route: String
) {
    navController.navigate(route) {
        popUpTo(
            route = navController.graph.findStartDestination().route!!
        ) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}