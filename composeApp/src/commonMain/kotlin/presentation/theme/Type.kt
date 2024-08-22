package presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.roboto_medium
import newsappkmp.composeapp.generated.resources.roboto_regular
import org.jetbrains.compose.resources.Font


private val baseline = Typography()

@Composable
fun getAppTypography() = Typography(
    displayLarge = baseline.displayLarge.setMediumTextSize(),
    displayMedium = baseline.displayMedium.setMediumTextSize(),
    displaySmall = baseline.displaySmall.setMediumTextSize(),
    headlineLarge = baseline.headlineLarge.setMediumTextSize(),
    headlineMedium = baseline.headlineMedium.setMediumTextSize(),
    headlineSmall = baseline.headlineSmall.setMediumTextSize(),
    titleLarge = baseline.titleLarge.setMediumTextSize(),
    titleMedium = baseline.titleMedium.setMediumTextSize(),
    titleSmall = baseline.titleSmall.setMediumTextSize(),
    bodyLarge = baseline.bodyLarge.setRegularTextSize(),
    bodyMedium = baseline.bodyMedium.setRegularTextSize(),
    bodySmall = baseline.bodySmall.setRegularTextSize(),
    labelLarge = baseline.labelLarge.setRegularTextSize(),
    labelMedium = baseline.labelMedium.setRegularTextSize(),
    labelSmall = baseline.labelSmall.setRegularTextSize(),
)

@Composable
fun TextStyle.setMediumTextSize() = this.copy(
    fontFamily = FontFamily(Font(Res.font.roboto_medium))
)

@Composable
fun TextStyle.setRegularTextSize() = this.copy(
    fontFamily = FontFamily(Font(Res.font.roboto_regular))
)