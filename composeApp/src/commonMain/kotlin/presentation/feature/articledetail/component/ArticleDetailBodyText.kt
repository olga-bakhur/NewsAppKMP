package presentation.feature.articledetail.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ArticleDetailBodyText(
    bodyText: String
) {
    Text(
        text = bodyText,
        fontWeight = FontWeight.Normal,
        style = MaterialTheme.typography.bodyLarge
    )
}