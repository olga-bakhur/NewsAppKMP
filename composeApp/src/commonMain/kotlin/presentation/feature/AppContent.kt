package presentation.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import presentation.navigation.AppBottomBar
import presentation.navigation.AppNavHost


@Composable
fun AppContent(
    startDestination: String,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    Box {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                AppBottomBar(
                    navController = navController,
                    modifier = modifier
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            contentWindowInsets = WindowInsets(0.dp),
        ) { paddingValues ->
            AppNavHost(
                navController = navController,
                startDestination = startDestination,
                paddingValues = paddingValues
            )
        }
    }
}