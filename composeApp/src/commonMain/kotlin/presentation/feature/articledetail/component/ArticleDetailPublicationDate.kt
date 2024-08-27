package presentation.feature.articledetail.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.published
import org.jetbrains.compose.resources.stringResource

@Composable
fun ArticleDetailPublicationDate(
    publicationDate: String
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(Res.string.published))
            }

            append(publicationDate)
        },
        fontWeight = FontWeight.Light,
        style = MaterialTheme.typography.bodyMedium
    )
}