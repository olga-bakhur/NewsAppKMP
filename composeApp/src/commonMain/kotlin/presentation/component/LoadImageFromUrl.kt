package presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
internal fun LoadImageFromUrl(
    imageUri: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    respectCacheHeaders: Boolean = false,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = imageUri,
            modifier = Modifier.fillMaxWidth(),
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