package presentation.feature.settings.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource
import presentation.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsShowBottomSheetEditTheme(
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