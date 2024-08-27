package presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun RoundImage(
    modifier: Modifier = Modifier,
    size: Dp,
    drawableResource: DrawableResource,
    contentDescription: String?,
    strokeWidth: Dp,
    contentScale: ContentScale = ContentScale.Crop,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null
) {

    Image(
        painter = painterResource(drawableResource),
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .border(
                border = BorderStroke(strokeWidth, MaterialTheme.colorScheme.primary),
                shape = CircleShape
            )
            .padding(strokeWidth)
            .clip(CircleShape),
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter
    )
}