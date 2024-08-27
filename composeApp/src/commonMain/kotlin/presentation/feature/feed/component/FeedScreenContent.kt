package presentation.feature.feed.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import app.cash.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.date
import newsappkmp.composeapp.generated.resources.screen_title_feed
import newsappkmp.composeapp.generated.resources.search
import org.jetbrains.compose.resources.stringResource
import presentation.component.BaseErrorDialog
import presentation.component.BasePagingList
import presentation.feature.feed.FeedState
import presentation.feature.feed.TopHeadlineItem
import presentation.navigation.navbar.TopAppBar
import presentation.navigation.navbar.TopAppBarActionItem
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
fun FeedScreenContent(
    state: FeedState,
    onArticleClicked: (articleId: String) -> Unit,
    onSaveArticleClicked: KFunction0<Unit>,
    onRemoveArticleClicked: KFunction0<Unit>,
    onFilterSelected: KFunction1<String?, Unit>,
    dismissError: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    scope: CoroutineScope,
    drawerState: DrawerState,
    dateRangePickerState: DateRangePickerState,
    showDateRangePicker: Boolean,
    onDatePickerClicked: () -> Unit,
    onDatePickerDismissed: () -> Unit,
    onRangeSelected: (Pair<Long?, Long?>?) -> Unit,
    isDateSelected: Boolean,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = stringResource(Res.string.screen_title_feed),
                maxLines = 1,
                fontFamily = FontFamily.Cursive,
                textStyle = MaterialTheme.typography.headlineMedium,
                scrollBehavior = scrollBehavior,
                isTopLevelDestination = true,
                onMenuClicked = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                },
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
                        isSelected = isDateSelected,
                        onActionClicked = {
                            onDatePickerClicked.invoke()
                        }
                    )
                )
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // DatePicker filter
            if (showDateRangePicker) {
                FeedDateRangePickerModal(
                    dateRangePickerState = dateRangePickerState,
                    onDateRangeSelected = {
                        onRangeSelected(it)
                    },
                    onDismiss = { onDatePickerDismissed.invoke() }
                )
            }

            // Feed
            val pagingItems = state.articles.collectAsLazyPagingItems()
            BasePagingList(
                modifier = Modifier.fillMaxSize(1F),
                data = pagingItems,
                contentTop = {
                    // Section Filters
                    FeedSectionFilters(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(align = Alignment.Top),
                        sections = state.sections,
                        onFilterSelected = onFilterSelected,
                        currentSectionId = state.feedFilter.sectionId
                    )
                }
            ) { articleView, _ ->
                articleView?.let { article ->
                    TopHeadlineItem(
                        article = article,
                        onArticleClicked = {
                            onArticleClicked(article.articleId)
                        },
                        onSaveArticleClicked = {
                            onSaveArticleClicked.invoke()
                        },
                        onRemoveArticleClicked = {
                            onRemoveArticleClicked.invoke()
                        }
                    )
                }
            }
        }

        // Snackbar add to favorites
        FeedSnackbarSaveOrRemoveArticle(
            isSaved = runBlocking { state.saveArticleResult.firstOrNull() },
            scope = scope,
            snackbarHostState = snackbarHostState,
            onRemoveArticleClicked = onRemoveArticleClicked
        )

        // Errors
        BaseErrorDialog(
            isError = state.errors.isNotEmpty(),
            error = state.errors.firstOrNull(),
            onDismiss = { dismissError.invoke() }
        )
    }
}