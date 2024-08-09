package presentation.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign


@Composable
fun BaseOneOptionDialog(
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
    contentDescription: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    confirmButtonLabel: String
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription
            )
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(
                text = dialogText,
                textAlign = TextAlign.Center
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(text = confirmButtonLabel)
            }
        }
    )
}