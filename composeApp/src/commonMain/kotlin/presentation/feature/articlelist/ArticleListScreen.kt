package presentation.feature.articlelist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.error
import newsappkmp.composeapp.generated.resources.no_info
import newsappkmp.composeapp.generated.resources.ok
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.navigation.Screen

@ExperimentalFoundationApi
@Composable
fun NewsListScreen(
    navController: NavController,
    viewModel: ArticleListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getPaginatedArticlesList()
    }

    ScreenContent(
        state = state,
        navController = navController
    )
}

@ExperimentalFoundationApi
@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    state: ArticleListState,
    navController: NavController
) {

    val articles = state.articles

    if (articles != null) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            items(
                items = articles,
                key = { it.articleId }
            ) { article ->
                TopHeadlineItem(
                    article = article,
                    onArticleClicked = {
                        navController.navigate(
                            route = Screen.ArticleDetail.createRoute(article.articleId)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(Res.string.no_info),
                modifier = modifier
                    .align(Alignment.Center)
            )
        }
    }

    if (state.loading) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = modifier
                    .align(Alignment.Center),
                color = Color.Red
            )
        }
    }

    state.error?.let { appError ->
        AlertDialog(
            onDismissRequest = {
                // TODO: dismiss
            },
            confirmButton = {
                Text(
                    text = stringResource(Res.string.ok)
                )
            },
            title = {
                Text(
                    text = stringResource(Res.string.error)
                )
            },
            text = {
                Text(
                    text = stringResource(appError.messageRes)
                )
            }
        )
    }
}