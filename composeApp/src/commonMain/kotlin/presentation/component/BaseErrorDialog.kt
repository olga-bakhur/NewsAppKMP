package presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import data.base.error.AppError
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.error
import newsappkmp.composeapp.generated.resources.ok
import org.jetbrains.compose.resources.stringResource


@Composable
fun BaseErrorDialog(
    isError: Boolean,
    error: AppError,
    onDismiss: () -> Unit
) {
    if (!isError) return

    BaseOneOptionDialog(
        onDismissRequest = {
            onDismiss.invoke()
        },
        onConfirmation = {
            onDismiss.invoke()
        },
        dialogTitle = stringResource(Res.string.error),
        dialogText = stringResource(error.messageRes),
        icon = Icons.Default.Info,
        contentDescription = stringResource(Res.string.error),
        confirmButtonLabel = stringResource(Res.string.ok)
    )
}