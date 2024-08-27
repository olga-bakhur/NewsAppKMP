package presentation.feature.feed.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.menu
import org.jetbrains.compose.resources.stringResource
import presentation.theme.Theme

@Composable
fun FeedNavigationDrawerContent(
    drawerState: DrawerState,
    selectedDrawerItem: Int,
    onSelectedDrawerItemChanged: (Int) -> Unit,
    scope: CoroutineScope
) {
    Text(
        text = stringResource(Res.string.menu),
        modifier = Modifier.padding(Theme.dimens.space16)
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

    Spacer(modifier = Modifier.height(Theme.dimens.space4))

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

    Spacer(modifier = Modifier.height(Theme.dimens.space16))

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