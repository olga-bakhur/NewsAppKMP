package presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val localDimens = staticCompositionLocalOf { Dimens() }
private val localRadius = staticCompositionLocalOf { Radius() }

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {

    CompositionLocalProvider(
        localDimens provides Dimens(),
        localRadius provides Radius(),
    ) {
        MaterialTheme(
            colorScheme = getColorScheme(isDarkTheme),
            shapes = getAppShapes(),
            typography = getAppTypography(),
            content = content
        )
    }
}

object Theme {
    val radius: Radius
        @Composable
        @ReadOnlyComposable
        get() = localRadius.current

    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = localDimens.current
}
