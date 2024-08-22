package presentation.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun getAppShapes() = Shapes(
    extraSmall = CutCornerShape(CornerSize.ExtraSmall.value),
    small = CutCornerShape(CornerSize.Small.value),
    medium = CutCornerShape(CornerSize.Medium.value),
    large = CutCornerShape(CornerSize.Large.value),
    extraLarge = CutCornerShape(CornerSize.ExtraLarge.value)
)

enum class CornerSize(val value: Dp) {
    ExtraSmall(4.dp),
    Small(8.dp),
    Medium(12.dp),
    Large(16.dp),
    ExtraLarge(24.dp)
}