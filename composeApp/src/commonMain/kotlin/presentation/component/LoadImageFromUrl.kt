package presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    contentAlignment: Alignment = Alignment.Center
) {
    Box(
        contentAlignment = contentAlignment,
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f),
            model = imageUri,
            placeholder = painterResource(Res.drawable.ic_image_placeholder),
            error = painterResource(Res.drawable.ic_image_error),
            contentScale = contentScale,
            contentDescription = contentDescription,
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
}