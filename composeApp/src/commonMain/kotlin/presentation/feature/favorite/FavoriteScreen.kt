package presentation.feature.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = koinViewModel(),
    onBackClicked: () -> Unit
) {
    Text("Favorite")
}