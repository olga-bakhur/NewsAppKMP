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
import presentation.feature.feed.SaveArticleResult
import kotlin.reflect.KFunction0

@Composable
fun FeedSnackbarSaveOrRemoveArticle(
    saveArticleResult: SaveArticleResult,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    onRemoveArticleClicked: KFunction0<Unit>
) {

    when (saveArticleResult) {
        SaveArticleResult.None -> return

        SaveArticleResult.Saved -> {
            scope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = getString(Res.string.saved),
                    actionLabel = getString(Res.string.undo),
                    duration = SnackbarDuration.Short
                )

                if (result == SnackbarResult.ActionPerformed) {
                    onRemoveArticleClicked.invoke()
                }
            }
        }

        SaveArticleResult.Removed -> {
            scope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = getString(Res.string.removed),
                    actionLabel = null,
                    duration = SnackbarDuration.Short
                )

                if (result == SnackbarResult.ActionPerformed) {
                    onRemoveArticleClicked.invoke()
                }
            }
        }
    }
}