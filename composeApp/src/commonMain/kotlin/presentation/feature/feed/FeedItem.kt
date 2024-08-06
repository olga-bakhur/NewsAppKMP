package presentation.feature.feed

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.model.dto.Article
import data.util.transformMillisToDateString
import presentation.component.LoadImageFromUrl

@Composable
fun TopHeadlineItem(
    article: Article,
    onArticleClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(
                border = BorderStroke(1.dp, Color.Black),
                RoundedCornerShape(10.dp)
            )
            .clickable { onArticleClicked() }
            .padding(16.dp)
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
            text = article.category,
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