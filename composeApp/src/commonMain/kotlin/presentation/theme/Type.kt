package presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.roboto_regular
import org.jetbrains.compose.resources.Font

private val baseline = Typography()

@Composable
fun getAppTypography() = Typography(
    displayLarge = baseline.displayLarge.updateFontFamily(),
    displayMedium = baseline.displayMedium.updateFontFamily(),
    displaySmall = baseline.displaySmall.updateFontFamily(),
    headlineLarge = baseline.headlineLarge.updateFontFamily(),
    headlineMedium = baseline.headlineMedium.updateFontFamily(),
    headlineSmall = baseline.headlineSmall.updateFontFamily(),
    titleLarge = baseline.titleLarge.updateFontFamily(),
    titleMedium = baseline.titleMedium.updateFontFamily(),
    titleSmall = baseline.titleSmall.updateFontFamily(),
    bodyLarge = baseline.bodyLarge.updateFontFamily(),
    bodyMedium = baseline.bodyMedium.updateFontFamily(),
    bodySmall = baseline.bodySmall.updateFontFamily(),
    labelLarge = baseline.labelLarge.updateFontFamily(),
    labelMedium = baseline.labelMedium.updateFontFamily(),
    labelSmall = baseline.labelSmall.updateFontFamily(),
)

@Composable
fun TextStyle.updateFontFamily() = this.copy(
    fontFamily = FontFamily(Font(Res.font.roboto_regular))
)