package presentation.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.screen_title_settings
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.BaseFloatingActionButton
import presentation.component.BaseOutlinedButton
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            BaseOutlinedButton(
                title = "Base Outlined Button",
                onClick = {

                }
            )

            Button(
                onClick = {

                }
            ) {
                Text("Filled")
            }

            OutlinedButton(
                onClick = {

                }
            ) {
                Text("OutlinedButton")
            }

            ElevatedButton(
                onClick = {

                }
            ) {
                Text("ElevatedButton")
            }

            TextButton(
                onClick = {

                }
            ) {
                Text("TextButton")
            }

            FilledTonalButton(
                onClick = {

                }
            ) {
                Text("FilledTonalButton")
            }
        }
    }
}