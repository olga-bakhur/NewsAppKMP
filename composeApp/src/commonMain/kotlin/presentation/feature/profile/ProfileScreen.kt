package presentation.feature.profile

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import presentation.feature.profile.component.ScreenContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onBackClicked: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var showDetails by remember { mutableStateOf(false) }

    ScreenContent(
        scrollBehavior = scrollBehavior,
        onBackClicked = onBackClicked,
        showDetails = showDetails,
        onShowDetailsChanged = { shouldShowDetails ->
            showDetails = shouldShowDetails
        }
    )
}




