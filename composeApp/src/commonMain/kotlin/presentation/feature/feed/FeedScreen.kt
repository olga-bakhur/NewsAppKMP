package presentation.feature.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import app.cash.paging.compose.collectAsLazyPagingItems
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.error
import newsappkmp.composeapp.generated.resources.ok
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.BasePagingList

@ExperimentalFoundationApi
@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = koinViewModel(),
    onArticleClicked: (articleId: String) -> Unit,
    onBackClicked: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getPaginatedArticlesList()
    }

    ScreenContent(
        modifier = modifier,
        state = state,
        onArticleClicked = onArticleClicked,
        onBackClicked = onBackClicked
    )
}

@ExperimentalFoundationApi
@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    state: FeedListState,
    onArticleClicked: (articleId: String) -> Unit,
    onBackClicked: () -> Unit,
) {
    val pagingItems = state.articles.collectAsLazyPagingItems()

    BasePagingList(
        modifier = modifier,
        data = pagingItems
    ) { articleView, modifierView ->
        articleView?.let { article ->
            TopHeadlineItem(
                article = article,
                onArticleClicked = {
                    onArticleClicked(article.articleId)
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
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
