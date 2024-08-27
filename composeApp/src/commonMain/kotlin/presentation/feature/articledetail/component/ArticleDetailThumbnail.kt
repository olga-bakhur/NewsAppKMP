package presentation.feature.articledetail.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.article_thumbnail_content_description
import org.jetbrains.compose.resources.stringResource
import presentation.component.LoadImageFromUrl

@Composable
fun ArticleDetailThumbnail(
    modifier: Modifier = Modifier,
    imageUri: String? = null
) {
    LoadImageFromUrl(
        imageUri = imageUri,
        contentDescription = stringResource(Res.string.article_thumbnail_content_description),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(10f / 6f),
        contentScale = ContentScale.FillWidth
    )
}