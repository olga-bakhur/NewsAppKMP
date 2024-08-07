package presentation.navigation.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    maxLines: Int,
    scrollBehavior: TopAppBarScrollBehavior,
    isNavigationIconVisible: Boolean,
    onNavigationIconClicked: (() -> Unit)? = null,
    actions: List<TopAppBarActionItem> = emptyList()
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (isNavigationIconVisible) {
                IconButton(
                    onClick = {
                        onNavigationIconClicked?.invoke()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.back)
                    )
                }
            }
        },
        actions = {
            actions.forEach { action ->
                with(action) {
                    IconButton(
                        onClick = { onActionClicked.invoke() }
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = contentDescription
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        scrollBehavior = scrollBehavior
    )
}