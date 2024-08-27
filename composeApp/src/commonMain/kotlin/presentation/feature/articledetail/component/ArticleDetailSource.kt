package presentation.feature.articledetail.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.source
import org.jetbrains.compose.resources.stringResource

@Composable
fun ArticleDetailSource(
    source: String
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(Res.string.source))
            }

            append(source)
        },
        fontWeight = FontWeight.Light,
        style = MaterialTheme.typography.bodyMedium
    )
}