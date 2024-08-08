package presentation.feature.feed

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.model.dto.Article
import data.util.transformMillisToDateString
import presentation.component.BaseCard
import presentation.component.LoadImageFromUrl

@Composable
fun TopHeadlineItem(
    article: Article,
    onArticleClicked: () -> Unit
) {
    BaseCard(
        modifier = Modifier.fillMaxWidth(),
        onCardClicked = {
            onArticleClicked.invoke()
        }
    ) {
        // Thumbnail
        LoadImageFromUrl(
            imageUri = article.thumbnail,
            contentDescription = "Thumbnail"
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Title
        Text(
            text = article.title,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Trail text
        Text(
            text = article.trailText,
            color = Color.Black,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Source
        Text(
            text = article.source,
            color = Color.Black,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Category
        Text(
            text = article.sectionName,
            color = Color.Black,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Date
        Text(
            text = transformMillisToDateString(article.publicationDate),
            color = Color.Black,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}