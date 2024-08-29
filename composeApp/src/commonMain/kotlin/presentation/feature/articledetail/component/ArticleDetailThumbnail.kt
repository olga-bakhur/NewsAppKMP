package presentation.feature.articledetail.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.article_thumbnail_content_description
import org.jetbrains.compose.resources.stringResource
import presentation.component.LoadImageFromUrl

@Composable
fun ArticleDetailThumbnail(
    imageUri: String? = null
) {
    LoadImageFromUrl(
        imageUri = imageUri,
        contentDescription = stringResource(Res.string.article_thumbnail_content_description),
        contentScale = ContentScale.FillWidth,
        aspectRatio = 10f / 6f
    )
}