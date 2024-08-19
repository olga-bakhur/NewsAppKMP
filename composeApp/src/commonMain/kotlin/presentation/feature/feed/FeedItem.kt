package presentation.feature.feed

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.model.dto.Article
import data.util.millisToFormattedDateString
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource
import presentation.component.BaseCard
import presentation.component.LoadImageFromUrl

@Composable
fun TopHeadlineItem(
    article: Article,
    onArticleClicked: () -> Unit,
    onSaveArticleClicked: () -> Unit,
    onRemoveArticleClicked: () -> Unit
) {
    BaseCard(
        modifier = Modifier.fillMaxWidth(),
        onCardClicked = {
            onArticleClicked.invoke()
        }
    ) {
        IconButton(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.End),
            onClick = {
                if (true /* !isSaved */) {
                    onSaveArticleClicked()
                } else {
                    onRemoveArticleClicked()
                }
            }
        ) {
            Icon(
                imageVector = if (false /* isSaved */) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Outlined.Favorite
                },
                contentDescription = stringResource(Res.string.save)
            )
        }

        // Thumbnail
        LoadImageFromUrl(
            imageUri = article.thumbnail,
            contentDescription = "Thumbnail",
            widthInPx = 500,
            heightInPx = 300
        )

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp
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
            text = millisToFormattedDateString(article.publicationDate),
            color = Color.Black,
            fontWeight = FontWeight.Light
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}