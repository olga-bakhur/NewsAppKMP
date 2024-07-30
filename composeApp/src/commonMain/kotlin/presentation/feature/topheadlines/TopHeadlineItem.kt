package presentation.feature.topheadlines

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
import common.EMPTY
import data.model.dto.Article

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
        Text(
            text = article.title ?: EMPTY,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = article.content ?: EMPTY,
            color = Color.Black,
            fontWeight = FontWeight.Light
        )
    }
}