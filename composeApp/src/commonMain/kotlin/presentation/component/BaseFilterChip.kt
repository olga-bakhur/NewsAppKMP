package presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BaseFilterChip(
    label: String,
    icon: ImageVector,
    onChipClicked: () -> Unit,
    selected: Boolean
) {
    FilterChip(
        label = {
            Text(label)
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