package presentation.feature.feed.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import presentation.feature.feed.FeedState
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FeedNavigationDrawer(
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
                FeedNavigationDrawerContent(
                    drawerState = drawerState,
                    selectedDrawerItem = selectedDrawerItem,
                    onSelectedDrawerItemChanged = onSelectedDrawerItemChanged,
                    scope = scope
                )
            }
        },
    ) {
        FeedScreenContent(
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