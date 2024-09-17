package presentation.feature.feed

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import presentation.feature.feed.component.FeedNavigationDrawer

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@ExperimentalFoundationApi
@Composable
fun FeedScreen(
    viewModel: FeedViewModel,
    onArticleClicked: (articleId: String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
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

    FeedNavigationDrawer(
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
        snackbarHostState = snackbarHostState,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope
    )
}