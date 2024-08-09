package presentation.feature.feed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ContextualFlowRowOverflowScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import app.cash.paging.compose.collectAsLazyPagingItems
import data.model.dto.Section
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.date
import newsappkmp.composeapp.generated.resources.filter
import newsappkmp.composeapp.generated.resources.less
import newsappkmp.composeapp.generated.resources.more_with_args
import newsappkmp.composeapp.generated.resources.screen_title_feed
import newsappkmp.composeapp.generated.resources.search
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.BaseFilterChip
import presentation.component.BasePagingList
import presentation.navigation.navbar.TopAppBar
import presentation.navigation.navbar.TopAppBarActionItem
import kotlin.reflect.KFunction1

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
        onFilterSelected = viewModel::setFilterBySection,
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
private fun ScreenContent(
    state: FeedListState,
    onArticleClicked: (articleId: String) -> Unit,
    onFilterSelected: KFunction1<String?, Unit>,
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Filters
            Filters(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.Top)
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 8.dp,
                    ),
                sections = state.sections,
                onFilterSelected = onFilterSelected
            )

            // Feed
            val pagingItems = state.articles.collectAsLazyPagingItems()
            BasePagingList(
                modifier = Modifier.fillMaxSize(1F),
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

        // TODO: errors
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Filters(
    modifier: Modifier = Modifier,
    sections: List<Section>,
    onFilterSelected: KFunction1<String?, Unit>
) {
    var maxLines by rememberSaveable() { mutableStateOf(2) }

    val moreOrCollapseIndicator = @Composable { scope: ContextualFlowRowOverflowScope ->
        val remainingItems = scope.totalItemCount - scope.shownItemCount
        OverflowFilterChip(
            remainingItems = remainingItems,
            maxLines = maxLines,
            onMaxLinesChange = { maxLines = it }
        )
    }

    ContextualFlowRow(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        maxLines = maxLines,
        overflow = ContextualFlowRowOverflow.expandOrCollapseIndicator(
            minRowsToShowCollapse = 4,
            expandIndicator = moreOrCollapseIndicator,
            collapseIndicator = moreOrCollapseIndicator
        ),
        itemCount = sections.size
    ) { index ->
        val section = sections[index]

        BaseFilterChip(
            label = section.sectionName,
            icon = Icons.Filled.Done,
            onChipClicked = {
                onFilterSelected.invoke(section.sectionId)
            }
        )
    }
}

@Composable
fun OverflowFilterChip(
    remainingItems: Int,
    maxLines: Int,
    onMaxLinesChange: (Int) -> Unit
) {
    SuggestionChip(
        label = {
            Text(
                text = if (remainingItems > 0) {
                    stringResource(
                        Res.string.more_with_args,
                        remainingItems
                    )
                } else {
                    stringResource(Res.string.less)
                }
            )
        },
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        onClick = {
            if (remainingItems > 0) {
                onMaxLinesChange(maxLines + 5)
            } else {
                onMaxLinesChange(2)
            }
        })
}


