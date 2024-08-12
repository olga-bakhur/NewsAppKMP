package presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BaseTextButton(
    text: String,
    icon: ImageVector? = null,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick.invoke() }
    ) {
        icon?.let {
            Icon(
                modifier = Modifier.padding(end = 4.dp),
                imageVector = icon,
                contentDescription = contentDescription
            )
        }

        Text(text = text)
    }
}