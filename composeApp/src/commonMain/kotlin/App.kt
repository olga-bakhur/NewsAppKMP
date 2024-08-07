import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import presentation.feature.AppContent
import presentation.navigation.Graph

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            AppContent(
                startDestination = Graph.NavigationBar.route
            )
        }
    }
}