package presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

@Composable
fun BaseFilterChip(
    label: String,
    fontFamily: FontFamily? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    icon: ImageVector,
    onChipClicked: () -> Unit,
    selected: Boolean
) {
    FilterChip(
        label = {
            Text(
                text = label,
                fontFamily = fontFamily,
                style = textStyle
            )
        },
        onClick = {
            onChipClicked.invoke()
        },
        selected = selected,
        leadingIcon = {
            if (selected) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        }
    )
}