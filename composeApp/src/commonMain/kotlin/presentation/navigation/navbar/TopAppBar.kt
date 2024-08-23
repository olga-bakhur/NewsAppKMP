package presentation.navigation.navbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.back
import newsappkmp.composeapp.generated.resources.menu
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    maxLines: Int,
    fontFamily: FontFamily? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    scrollBehavior: TopAppBarScrollBehavior,
    isTopLevelDestination: Boolean,
    onMenuClicked: (() -> Unit)? = null,
    onBackClicked: (() -> Unit)? = null,
    actions: List<TopAppBarActionItem> = emptyList()
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                maxLines = maxLines,
                overflow = TextOverflow.Ellipsis,
                fontFamily = fontFamily,
                style = textStyle
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (isTopLevelDestination) {
                IconButton(
                    onClick = {
                        onMenuClicked?.invoke()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(Res.string.menu)
                    )
                }
            } else {
                IconButton(
                    onClick = {
                        onBackClicked?.invoke()
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
                            contentDescription = contentDescription,
                            tint = if (isSelected) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                LocalContentColor.current
                            }
                        )
                    }
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}