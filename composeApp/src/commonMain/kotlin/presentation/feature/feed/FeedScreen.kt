package presentation.feature.feed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ContextualFlowRowOverflowScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.cash.paging.compose.collectAsLazyPagingItems
import data.model.dto.Section
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.apply
import newsappkmp.composeapp.generated.resources.clear
import newsappkmp.composeapp.generated.resources.close
import newsappkmp.composeapp.generated.resources.date
import newsappkmp.composeapp.generated.resources.less
import newsappkmp.composeapp.generated.resources.menu
import newsappkmp.composeapp.generated.resources.more_with_args
import newsappkmp.composeapp.generated.resources.removed
import newsappkmp.composeapp.generated.resources.saved
import newsappkmp.composeapp.generated.resources.screen_title_feed
import newsappkmp.composeapp.generated.resources.search
import newsappkmp.composeapp.generated.resources.undo
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.BaseErrorDialog
import presentation.component.BaseFilterChip
import presentation.component.BasePagingList
import presentation.navigation.navbar.TopAppBar
import presentation.navigation.navbar.TopAppBarActionItem
import kotlin.reflect.KFunction0
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
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.feedFilter) {
        viewModel.fetchSections()
        viewModel.getPaginatedArticlesList()
    }

    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectedDrawerItem by rememberSaveable { mutableStateOf(0) }

    val dateRangePickerState = rememberDateRangePickerState()
    var showDateRangePicker by rememberSaveable { mutableStateOf(false) }
    val isDateSelected = state.feedFilter.fromDate != null || state.feedFilter.toDate != null

    val snackbarHostState = remember { SnackbarHostState() }

    NavigationDrawer(
        state = state,
        onArticleClicked = onArticleClicked,
        onSaveArticleClicked = viewModel::saveArticle,
        onRemoveArticleClicked = viewModel::removeArticle,
        onFilterSelected = viewModel::setFilterBySection,
        dismissError = viewModel::dismissError,
        scrollBehavior = scrollBehavior,
        drawerState = drawerState,
        selectedDrawerItem = selectedDrawerItem,
        scope = scope,
        onSelectedDrawerItemChanged = { selectedDrawerItem = it },
        dateRangePickerState = dateRangePickerState,
        showDateRangePicker = showDateRangePicker,
        onDatePickerClicked = {
            showDateRangePicker = true
        },
        onDatePickerDismissed = {
            showDateRangePicker = false
        },
        onRangeSelected = {
            viewModel.setFilterByDate(
                fromDate = it?.first,
                toDate = it?.second
            )
        },
        isDateSelected = isDateSelected,
        snackbarHostState = snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NavigationDrawer(
    state: FeedState,
    onArticleClicked: (articleId: String) -> Unit,
    onSaveArticleClicked: KFunction0<Unit>,
    onRemoveArticleClicked: KFunction0<Unit>,
    onFilterSelected: KFunction1<String?, Unit>,
    dismissError: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    drawerState: DrawerState,
    selectedDrawerItem: Int,
    onSelectedDrawerItemChanged: (Int) -> Unit,
    dateRangePickerState: DateRangePickerState,
    showDateRangePicker: Boolean,
    onDatePickerClicked: () -> Unit,
    onDatePickerDismissed: () -> Unit,
    onRangeSelected: (Pair<Long?, Long?>?) -> Unit,
    isDateSelected: Boolean,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerContent(
                    drawerState = drawerState,
                    selectedDrawerItem = selectedDrawerItem,
                    onSelectedDrawerItemChanged = onSelectedDrawerItemChanged,
                    scope = scope
                )
            }
        },
    ) {
        ScreenContent(
            state = state,
            onArticleClicked = onArticleClicked,
            onSaveArticleClicked = onSaveArticleClicked,
            onRemoveArticleClicked = onRemoveArticleClicked,
            onFilterSelected = onFilterSelected,
            dismissError = dismissError,
            scrollBehavior = scrollBehavior,
            scope = scope,
            drawerState = drawerState,
            dateRangePickerState = dateRangePickerState,
            showDateRangePicker = showDateRangePicker,
            onDatePickerClicked = onDatePickerClicked,
            onDatePickerDismissed = onDatePickerDismissed,
            onRangeSelected = onRangeSelected,
            isDateSelected = isDateSelected,
            snackbarHostState = snackbarHostState
        )
    }
}

@Composable
fun NavigationDrawerContent(
    drawerState: DrawerState,
    selectedDrawerItem: Int,
    onSelectedDrawerItemChanged: (Int) -> Unit,
    scope: CoroutineScope
) {
    Text(
        text = stringResource(Res.string.menu),
        modifier = Modifier.padding(16.dp)
    )

    HorizontalDivider()

    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        label = { Text(text = "Item 1") },
        selected = selectedDrawerItem == 1,
        onClick = {
            onSelectedDrawerItemChanged.invoke(1)

            scope.launch {
                drawerState.close()
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info"
            )
        },
        badge = {
            Text(text = "12")
        }
    )

    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        label = { Text(text = "Item 2") },
        selected = selectedDrawerItem == 2,
        onClick = {
            onSelectedDrawerItemChanged.invoke(2)

            scope.launch {
                drawerState.close()
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info"
            )
        }
    )

    Spacer(modifier = Modifier.height(4.dp))

    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        label = { Text(text = "Item 3") },
        selected = selectedDrawerItem == 3,
        onClick = {
            onSelectedDrawerItemChanged.invoke(3)

            scope.launch {
                drawerState.close()
            }
        },
        icon = {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info"
            )
        },
        badge = {
            Text(text = "54")
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
private fun ScreenContent(
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
                DateRangePickerModal(
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
                    SectionFilters(
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
        state.saveArticleResult?.let { isSaved ->
            SnackbarSaveOrRemoveArticle(
                isSaved = isSaved,
                scope = scope,
                snackbarHostState = snackbarHostState,
                onRemoveArticleClicked = onRemoveArticleClicked
            )
        }

        // Errors
        if (state.errors.isNotEmpty()) {
            BaseErrorDialog(
                error = state.errors.first(),
                onDismiss = { dismissError.invoke() }
            )
        }
    }
}

@Composable
fun SnackbarSaveOrRemoveArticle(
    isSaved: Boolean,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onRemoveArticleClicked: KFunction0<Unit>
) {
    scope.launch {
        val result = snackbarHostState.showSnackbar(
            message = if (isSaved) {
                getString(Res.string.saved)
            } else {
                getString(Res.string.removed)
            },
            actionLabel = if (isSaved) {
                getString(Res.string.undo)
            } else {
                null
            },
            duration = SnackbarDuration.Short
        )

        if (result == SnackbarResult.ActionPerformed) {
            onRemoveArticleClicked.invoke()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SectionFilters(
    modifier: Modifier = Modifier,
    sections: List<Section>,
    onFilterSelected: KFunction1<String?, Unit>,
    currentSectionId: String?
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
            },
            selected = currentSectionId == section.sectionId
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    dateRangePickerState: DateRangePickerState,
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            ) {
                Text(text = stringResource(Res.string.apply))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(null, null)
                    )
                    onDismiss()
                }) {
                Text(text = stringResource(Res.string.clear))
            }
        }
    ) {
        Box {
            DateRangePicker(
                state = dateRangePickerState,
                showModeToggle = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            )

            IconButton(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.TopEnd),
                onClick = onDismiss
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(Res.string.close)
                )
            }
        }
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