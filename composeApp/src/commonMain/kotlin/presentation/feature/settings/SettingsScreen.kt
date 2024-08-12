package presentation.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.contact_us
import newsappkmp.composeapp.generated.resources.inbox
import newsappkmp.composeapp.generated.resources.screen_title_settings
import newsappkmp.composeapp.generated.resources.theme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.BaseFloatingActionButton
import presentation.component.BaseTextButton
import presentation.navigation.navbar.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel(),
    onBackClicked: () -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    ScreenContent(
        scrollBehavior = scrollBehavior,
        onBackClicked = onBackClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    scrollBehavior: TopAppBarScrollBehavior,
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
                scrollBehavior = scrollBehavior,
                isNavigationIconVisible = true,
                onNavigationIconClicked = {
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
        var itemCount by rememberSaveable { mutableStateOf(20) }

        ScreenContent(
            itemCount = itemCount,
            paddingValues = paddingValues
        )
    }
}

@Composable
fun ScreenContent(
    itemCount: Int,
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            BaseTextButton(
                text = stringResource(Res.string.inbox),
                icon = Icons.Filled.Notifications,
                contentDescription = stringResource(Res.string.inbox),
                onClick = {
                    // TODO
                }
            )

            if (itemCount > 0) {
                Badge(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ) {
                    Text("$itemCount")
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        BaseTextButton(
            text = stringResource(Res.string.theme),
            icon = Icons.Filled.Edit,
            contentDescription = stringResource(Res.string.theme),
            onClick = {
                // TODO
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        BaseTextButton(
            text = stringResource(Res.string.contact_us),
            icon = Icons.Filled.Email,
            contentDescription = stringResource(Res.string.contact_us),
            onClick = {
                // TODO
            }
        )
    }
}