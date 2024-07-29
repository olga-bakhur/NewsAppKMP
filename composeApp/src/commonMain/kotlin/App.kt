import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import presentation.viewModel.TopHeadlinesViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        Text("NewsAppKMP")

        KoinContext {
            // TODO: remove test implementation
            val topHeadlinesViewModel = koinViewModel<TopHeadlinesViewModel>()
            topHeadlinesViewModel.getTopHeadlines()
        }
    }
}