package presentation.feature.topheadlines

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import data.model.dto.Article
import org.koin.compose.viewmodel.koinViewModel

@ExperimentalFoundationApi
@Composable
fun TopHeadlinesScreen(
    navController: NavController,
    viewModel: TopHeadlinesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchHeadlines()
    }

    ScreenContent(
        articles = state.articles,
    )
}

@ExperimentalFoundationApi
@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    articles: List<Article>?,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
    ) {
        if (articles != null) {
            items(
                items = articles,
                key = { it.url!! }
            ) { article ->
                TopHeadlineItem(
                    article = article,
                    onArticleClicked = {
                        // TODO
//                        navController.navigate("note_detail/${note.id}")
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .animateItemPlacement()
                )
            }
        } else {
            //todo: add loading or empty list placeholder
        }
    }
}