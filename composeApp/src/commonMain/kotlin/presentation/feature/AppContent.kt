package presentation.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import presentation.navigation.AppNavHost
import presentation.navigation.navbar.BottomNavBar
import presentation.navigation.navbar.SideNavBar


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppContent(
    startDestination: String,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val isSideBarNavigation =
        calculateWindowSizeClass().widthSizeClass != WindowWidthSizeClass.Compact

    Box {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                if (!isSideBarNavigation) {
                    BottomNavBar(
                        navController = navController,
                        modifier = modifier
                    )
                }
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            contentWindowInsets = WindowInsets(0.dp),
        ) { paddingValues ->
            AppNavHost(
                navController = navController,
                startDestination = startDestination,
                paddingValues = paddingValues,
                modifier = modifier.padding(start = if (isSideBarNavigation) 80.dp else 0.dp)
            )
        }

        if (isSideBarNavigation) {
            SideNavBar(
                navController = navController,
                modifier = modifier
            )
        }
    }
}