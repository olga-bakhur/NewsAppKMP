package presentation.feature.articledetail.component

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.article_thumbnail_content_description
import org.jetbrains.compose.resources.stringResource
import presentation.component.LoadImageFromUrl

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ArticleDetailThumbnail(
    imageUri: String? = null,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        LoadImageFromUrl(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = sharedTransitionScope.rememberSharedContentState(key = "image-$imageUri"),
                    animatedVisibilityScope = animatedContentScope
                ),
            imageUri = imageUri,
            contentDescription = stringResource(Res.string.article_thumbnail_content_description),
            contentScale = ContentScale.FillWidth,
            aspectRatio = 10f / 6f
        )
    }
}