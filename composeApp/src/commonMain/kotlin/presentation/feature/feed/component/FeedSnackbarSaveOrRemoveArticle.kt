package presentation.feature.feed.component

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.removed
import newsappkmp.composeapp.generated.resources.saved
import newsappkmp.composeapp.generated.resources.undo
import org.jetbrains.compose.resources.getString
import kotlin.reflect.KFunction0

@Composable
fun FeedSnackbarSaveOrRemoveArticle(
    isSaved: Boolean?,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onRemoveArticleClicked: KFunction0<Unit>
) {
    if (isSaved == null) return

    scope.launch {
        val result = snackbarHostState.showSnackbar(
            message = if (isSaved) {
                getString(Res.string.saved)
            } else {
                getString(Res.string.removed)
            },
            actionLabel = if (isSaved) {
                getString(Res.string.undo)
            } else {
                null
            },
            duration = SnackbarDuration.Short
        )

        if (result == SnackbarResult.ActionPerformed) {
            onRemoveArticleClicked.invoke()
        }
    }
}