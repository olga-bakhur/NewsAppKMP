import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import presentation.feature.AppContent
import presentation.navigation.Graph
import presentation.theme.AppTheme

@Composable
@Preview
fun App() {
    KoinContext {
        AppTheme {
            AppContent(
                startDestination = Graph.NavigationBar.route
            )
        }
    }
}