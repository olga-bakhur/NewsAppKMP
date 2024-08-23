package presentation.feature.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import common.EMPTY
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.screen_title_profile
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.navigation.navbar.TopAppBar
import presentation.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
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
                title = stringResource(Res.string.screen_title_profile),
                maxLines = 1,
                fontFamily = FontFamily.Cursive,
                textStyle = MaterialTheme.typography.headlineMedium,
                scrollBehavior = scrollBehavior,
                isTopLevelDestination = false,
                onBackClicked = {
                    onBackClicked.invoke()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = Theme.dimens.space16)
        ) {
            UserNameInputField(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun UserNameInputField(
    modifier: Modifier = Modifier
) {
    var text by rememberSaveable { mutableStateOf(EMPTY) }

    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = { text = it },
        label = { Text("Name") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "User name",
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    text = EMPTY
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear user name",
                )
            }

        },
        singleLine = true
    )
}