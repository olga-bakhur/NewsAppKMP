package presentation.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.ic_image_error
import newsappkmp.composeapp.generated.resources.ic_image_placeholder
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun LoadImageFromUrl(
    imageUri: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    aspectRatio: Float
) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(aspectRatio),
        model = imageUri,
        contentDescription = contentDescription,
        placeholder = painterResource(Res.drawable.ic_image_placeholder),
        error = painterResource(Res.drawable.ic_image_error),
        contentScale = contentScale,
        onError = {
            //update state
        },
        onLoading = {
            //update state
        },
        onSuccess = {
            //update state
        },
    )
}