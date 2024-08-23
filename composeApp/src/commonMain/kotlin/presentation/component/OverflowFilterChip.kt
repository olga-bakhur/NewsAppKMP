package presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.less
import newsappkmp.composeapp.generated.resources.more_with_args
import org.jetbrains.compose.resources.stringResource
import presentation.theme.Theme

@Composable
fun OverflowFilterChip(
    remainingItems: Int,
    maxLines: Int,
    onMaxLinesChange: (Int) -> Unit,
    fontFamily: FontFamily? = null,
    textStyle: TextStyle = LocalTextStyle.current
) {
    SuggestionChip(
        label = {
            Text(
                text = if (remainingItems > 0) {
                    stringResource(
                        Res.string.more_with_args,
                        remainingItems
                    )
                } else {
                    stringResource(Res.string.less)
                },
                fontFamily = fontFamily,
                style = textStyle
            )
        },
        border = BorderStroke(Theme.dimens.space1, MaterialTheme.colorScheme.primary),
        onClick = {
            if (remainingItems > 0) {
                onMaxLinesChange(maxLines + 5)
            } else {
                onMaxLinesChange(2)
            }
        })
}