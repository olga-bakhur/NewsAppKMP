package presentation.feature.feed.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.apply
import newsappkmp.composeapp.generated.resources.clear
import newsappkmp.composeapp.generated.resources.close
import org.jetbrains.compose.resources.stringResource
import presentation.theme.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedDateRangePickerModal(
    dateRangePickerState: DateRangePickerState,
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            ) {
                Text(text = stringResource(Res.string.apply))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDateRangeSelected(
                        Pair(null, null)
                    )
                    onDismiss()
                }) {
                Text(text = stringResource(Res.string.clear))
            }
        }
    ) {
        Box {
            DateRangePicker(
                state = dateRangePickerState,
                showModeToggle = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(Theme.dimens.space16)
            )

            IconButton(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.TopEnd),
                onClick = onDismiss
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(Res.string.close)
                )
            }
        }
    }
}