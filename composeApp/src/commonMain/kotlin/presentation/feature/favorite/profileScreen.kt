package presentation.feature.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel
import presentation.feature.profile.ProfileViewModel

@Composable
fun FavoriteScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    onBackClicked: () -> Unit
) {
    Text("Favorite")
}