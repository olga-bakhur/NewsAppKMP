package presentation.feature.profile.component

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.compose_multiplatform
import newsappkmp.composeapp.generated.resources.user_avatar_content_description
import org.jetbrains.compose.resources.stringResource
import presentation.component.RoundImage
import presentation.theme.Theme

@Composable
fun ProfileAvatar(
    modifier: Modifier = Modifier
) {
    RoundImage(
        modifier = modifier
            .padding(Theme.dimens.space8)
            .graphicsLayer {
                compositingStrategy = CompositingStrategy.Offscreen
            }
            .drawWithCache {
                val path = Path()
                path.addOval(
                    Rect(
                        topLeft = Offset.Zero,
                        bottomRight = Offset(size.width, size.height)
                    )
                )
                onDrawWithContent {
                    clipPath(path) {
                        this@onDrawWithContent.drawContent()
                    }
                    val dotSize = size.width / 8f
                    drawCircle(
                        Color.Black,
                        radius = dotSize,
                        center = Offset(
                            x = size.width - dotSize,
                            y = size.height - dotSize
                        ),
                        blendMode = BlendMode.Clear
                    )
                    drawCircle(
                        Color(0xFFEF5350), radius = dotSize * 0.8f,
                        center = Offset(
                            x = size.width - dotSize,
                            y = size.height - dotSize
                        )
                    )
                }
            },
        size = Theme.dimens.space200,
        strokeWidth = Theme.dimens.space1,
        drawableResource = Res.drawable.compose_multiplatform,
        contentDescription = stringResource(Res.string.user_avatar_content_description)
    )
}