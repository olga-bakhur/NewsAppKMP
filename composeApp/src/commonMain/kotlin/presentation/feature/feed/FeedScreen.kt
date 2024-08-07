package presentation.feature.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import app.cash.paging.compose.collectAsLazyPagingItems
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.screen_title_feed
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.BasePagingList
import presentation.navigation.navbar.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun FeedScreen(
    viewModel: FeedViewModel = koinViewModel(),
    onArticleClicked: (articleId: String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getPaginatedArticlesList()
    }

    val state by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    ScreenContent(
        state = state,
        onArticleClicked = onArticleClicked,
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
private fun ScreenContent(
    state: FeedListState,
    onArticleClicked: (articleId: String) -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = stringResource(Res.string.screen_title_feed),
                maxLines = 1,
                scrollBehavior = scrollBehavior,
                isNavigationIconVisible = false
            )
        }
    ) { innerPadding ->
        val pagingItems = state.articles.collectAsLazyPagingItems()

        BasePagingList(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            data = pagingItems
        ) { articleView, _ ->
            articleView?.let { article ->
                TopHeadlineItem(
                    article = article,
                    onArticleClicked = {
                        onArticleClicked(article.articleId)
                    }
                )
            }
        }
    }
}
