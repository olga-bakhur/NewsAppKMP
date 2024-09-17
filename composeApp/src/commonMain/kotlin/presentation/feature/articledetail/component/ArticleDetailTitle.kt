package presentation.feature.articledetail.component

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArticleDetailTitle(
    title: String,
    articleId: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        Text(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = sharedTransitionScope.rememberSharedContentState(key = "text-$articleId"),
                    animatedVisibilityScope = animatedContentScope,
                ),
            text = title,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.titleMedium
        )
    }
}