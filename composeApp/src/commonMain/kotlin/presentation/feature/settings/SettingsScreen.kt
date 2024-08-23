package presentation.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.contact_us
import newsappkmp.composeapp.generated.resources.inbox
import newsappkmp.composeapp.generated.resources.save
import newsappkmp.composeapp.generated.resources.screen_title_settings
import newsappkmp.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.BaseFloatingActionButton
import presentation.component.BaseTextButton
import presentation.navigation.navbar.TopAppBar
import presentation.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel(),
    onBackClicked: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    var inboxCount by rememberSaveable { mutableStateOf(20) }

    val scope = rememberCoroutineScope()
    val editThemeBottomSheetState = rememberModalBottomSheetState()
    var showEditThemeBottomSheet by rememberSaveable { mutableStateOf(false) }

    ScreenContent(
        scrollBehavior = scrollBehavior,
        inboxCount = inboxCount,
        scope = scope,
        showEditThemeBottomSheet = showEditThemeBottomSheet,
        editThemeBottomSheetState = editThemeBottomSheetState,
        onShowBottomSheetEditThemeChanged = { showEditThemeBottomSheet = it },
        onBackClicked = onBackClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    scrollBehavior: TopAppBarScrollBehavior,
    inboxCount: Int,
    scope: CoroutineScope,
    showEditThemeBottomSheet: Boolean,
    editThemeBottomSheetState: SheetState,
    onShowBottomSheetEditThemeChanged: (Boolean) -> Unit,
    onBackClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = stringResource(Res.string.screen_title_settings),
                maxLines = 1,
                fontFamily = FontFamily.Cursive,
                textStyle = MaterialTheme.typography.headlineMedium,
                scrollBehavior = scrollBehavior,
                isTopLevelDestination = false,
                onBackClicked = {
                    onBackClicked.invoke()
                }
            )
        },
        floatingActionButton = {
            BaseFloatingActionButton(
                imageVector = Icons.Default.Done,
                contentDescription = "Done",
                onClick = {

                }
            )
        }
    ) { paddingValues ->
        /* Test value */
        ScreenContent(
            paddingValues = paddingValues,
            inboxCount = inboxCount,
            scope = scope,
            showEditThemeBottomSheet = showEditThemeBottomSheet,
            editThemeBottomSheetState = editThemeBottomSheetState,
            onShowBottomSheetEditThemeChanged = onShowBottomSheetEditThemeChanged
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(
    paddingValues: PaddingValues,
    inboxCount: Int,
    scope: CoroutineScope,
    showEditThemeBottomSheet: Boolean,
    editThemeBottomSheetState: SheetState,
    onShowBottomSheetEditThemeChanged: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(
                start = Theme.dimens.space16,
                end = Theme.dimens.space16,
                bottom = Theme.dimens.space16
            )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            BaseTextButton(
                text = stringResource(Res.string.inbox),
                fontFamily = FontFamily.Cursive,
                textStyle = MaterialTheme.typography.titleLarge,
                icon = Icons.Filled.Notifications,
                contentDescription = stringResource(Res.string.inbox),
                onClick = {
                    // TODO
                }
            )

            if (inboxCount > 0) {
                Badge(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ) {
                    Text(
                        text = "$inboxCount",
                        fontFamily = FontFamily.Cursive
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(Theme.dimens.space8))

        BaseTextButton(
            text = stringResource(Res.string.theme),
            fontFamily = FontFamily.Cursive,
            textStyle = MaterialTheme.typography.titleLarge,
            icon = Icons.Filled.Edit,
            contentDescription = stringResource(Res.string.theme),
            onClick = {
                onShowBottomSheetEditThemeChanged.invoke(true)
            }
        )

        Spacer(modifier = Modifier.height(Theme.dimens.space8))

        BaseTextButton(
            text = stringResource(Res.string.contact_us),
            fontFamily = FontFamily.Cursive,
            textStyle = MaterialTheme.typography.titleLarge,
            icon = Icons.Filled.Email,
            contentDescription = stringResource(Res.string.contact_us),
            onClick = {
                // TODO
            }
        )

        ShowBottomSheetEditTheme(
            scope = scope,
            showEditThemeBottomSheet = showEditThemeBottomSheet,
            editThemeBottomSheetState = editThemeBottomSheetState,
            onShowBottomSheetEditThemeChanged = onShowBottomSheetEditThemeChanged
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowBottomSheetEditTheme(
    scope: CoroutineScope,
    showEditThemeBottomSheet: Boolean,
    editThemeBottomSheetState: SheetState,
    onShowBottomSheetEditThemeChanged: (Boolean) -> Unit
) {
    if (showEditThemeBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                onShowBottomSheetEditThemeChanged.invoke(false)
            },
            sheetState = editThemeBottomSheetState
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier
                        .padding(Theme.dimens.space16)
                        .align(Alignment.CenterHorizontally),
                    onClick = {
                        scope.launch { editThemeBottomSheetState.hide() }.invokeOnCompletion {
                            if (!editThemeBottomSheetState.isVisible) {
                                onShowBottomSheetEditThemeChanged.invoke(false)
                            }
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier.padding(end = Theme.dimens.space8),
                        imageVector = Icons.Default.Done,
                        contentDescription = stringResource(Res.string.save),
                    )
                    Text(text = stringResource(Res.string.save))
                }
            }
        }
    }
}