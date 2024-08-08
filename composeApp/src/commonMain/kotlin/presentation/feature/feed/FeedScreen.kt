package presentation.feature.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
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
import newsappkmp.composeapp.generated.resources.date
import newsappkmp.composeapp.generated.resources.filter
import newsappkmp.composeapp.generated.resources.screen_title_feed
import newsappkmp.composeapp.generated.resources.search
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.BasePagingList
import presentation.navigation.navbar.TopAppBar
import presentation.navigation.navbar.TopAppBarActionItem

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun FeedScreen(
    viewModel: FeedViewModel = koinViewModel(),
    onArticleClicked: (articleId: String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.fetchSections()
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
                isNavigationIconVisible = false,
                actions = listOf(
                    TopAppBarActionItem(
                        icon = Icons.Outlined.Search,
                        contentDescription = stringResource(Res.string.search),
                        onActionClicked = {
                            println("Search clicked")
                        }
                    ),
                    TopAppBarActionItem(
                        icon = Icons.Outlined.DateRange,
                        contentDescription = stringResource(Res.string.date),
                        onActionClicked = {
                            println("DateRange clicked")
                        }
                    ),
                    TopAppBarActionItem(
                        // TODO: must be a Filter icon
                        icon = Icons.Outlined.MoreVert,
                        contentDescription = stringResource(Res.string.filter),
                        onActionClicked = {
                            println("Filter clicked")
                        }
                    )
                )
            )
        }
    ) { paddingValues ->
        val pagingItems = state.articles.collectAsLazyPagingItems()

        BasePagingList(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
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
