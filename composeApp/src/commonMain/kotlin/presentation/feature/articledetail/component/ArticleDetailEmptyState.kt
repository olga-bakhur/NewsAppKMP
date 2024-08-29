package presentation.feature.articledetail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.no_info
import org.jetbrains.compose.resources.stringResource

@Composable
fun ArticleDetailEmptyState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(Res.string.no_info)
        )
    }
}