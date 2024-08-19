package presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
    widthInPx: Int? = null,
    heightInPx: Int? = null,
    contentScale: ContentScale = ContentScale.Inside
) {
    var widthInDp: Dp? = null
    var heightInDp: Dp? = null

    if (widthInPx != null && heightInPx != null) {
        val density = LocalDensity.current.density
        widthInDp = (widthInPx?.div(density))?.dp
        heightInDp = (heightInPx?.div(density))?.dp
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            modifier = if (widthInDp != null && heightInDp != null) {
                Modifier.size(widthInDp, heightInDp)
            } else {
                Modifier.fillMaxWidth()
            },
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