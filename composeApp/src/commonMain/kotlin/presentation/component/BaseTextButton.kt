package presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import presentation.theme.Theme

@Composable
fun BaseTextButton(
    text: String,
    fontFamily: FontFamily? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    icon: ImageVector? = null,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick.invoke() }
    ) {
        icon?.let {
            Icon(
                modifier = Modifier.padding(end = Theme.dimens.space4),
                imageVector = icon,
                contentDescription = contentDescription
            )
        }

        Text(
            text = text,
            fontFamily = fontFamily,
            style = textStyle
        )
    }
}