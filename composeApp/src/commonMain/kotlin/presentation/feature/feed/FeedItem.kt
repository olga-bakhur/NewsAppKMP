package presentation.feature.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import data.model.dto.Article
import data.util.millisToFormattedDateString
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.published
import newsappkmp.composeapp.generated.resources.save
import newsappkmp.composeapp.generated.resources.section
import newsappkmp.composeapp.generated.resources.source
import org.jetbrains.compose.resources.stringResource
import presentation.component.BaseCard
import presentation.component.LoadImageFromUrl
import presentation.theme.Theme

@Composable
fun TopHeadlineItem(
    article: Article,
    onArticleClicked: () -> Unit,
    onSaveArticleClicked: () -> Unit,
    onRemoveArticleClicked: () -> Unit
) {
    BaseCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top),
        contentPadding = Theme.dimens.space0,
        onCardClicked = {
            onArticleClicked.invoke()
        }
    ) {
        Thumbnail(imageUri = article.thumbnail)
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = Theme.dimens.space1
        )
        Spacer(modifier = Modifier.height(Theme.dimens.space8))

        Title(title = article.title)
        Spacer(modifier = Modifier.height(Theme.dimens.space8))

        TrailText(trailText = article.trailText)
        Spacer(modifier = Modifier.height(Theme.dimens.space8))

        Row(
            modifier = Modifier.padding(bottom = Theme.dimens.space16)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Source(source = article.source)
                Spacer(modifier = Modifier.height(Theme.dimens.space4))

                Section(section = article.sectionName)
                Spacer(modifier = Modifier.height(Theme.dimens.space4))

                PublicationDate(publicationDate = millisToFormattedDateString(article.publicationDate))
            }

            SaveIconButton(
                onSaveArticleClicked = onSaveArticleClicked,
                onRemoveArticleClicked = onRemoveArticleClicked,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .wrapContentSize()

            )
        }
    }
}

@Composable
fun SaveIconButton(
    modifier: Modifier = Modifier,
    onSaveArticleClicked: () -> Unit,
    onRemoveArticleClicked: () -> Unit
) {
    IconButton(
        modifier = modifier,
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
                Icons.Filled.Star
            } else {
                Icons.Outlined.Star
            },
            contentDescription = stringResource(Res.string.save)
        )
    }
}

@Composable
fun Thumbnail(
    modifier: Modifier = Modifier,
    imageUri: String? = null
) {
    LoadImageFromUrl(
        imageUri = imageUri,
        contentDescription = "Thumbnail",
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(10f / 6f),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun Title(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        modifier = modifier.padding(
            start = Theme.dimens.space16,
            end = Theme.dimens.space16
        ),
        text = title,
        fontWeight = FontWeight.SemiBold,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun TrailText(
    modifier: Modifier = Modifier,
    trailText: String
) {
    Text(
        modifier = modifier.padding(
            start = Theme.dimens.space16,
            end = Theme.dimens.space16
        ),
        text = trailText,
        fontWeight = FontWeight.Normal,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun Source(
    modifier: Modifier = Modifier,
    source: String
) {
    Text(
        modifier = modifier.padding(
            start = Theme.dimens.space16,
            end = Theme.dimens.space16
        ),
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
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun Section(
    modifier: Modifier = Modifier,
    section: String
) {
    Text(
        modifier = modifier.padding(
            start = Theme.dimens.space16,
            end = Theme.dimens.space16
        ),
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(Res.string.section))
            }

            append(section)
        },
        fontWeight = FontWeight.Light,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun PublicationDate(
    modifier: Modifier = Modifier,
    publicationDate: String
) {
    Text(
        modifier = modifier.padding(
            start = Theme.dimens.space16,
            end = Theme.dimens.space16
        ),
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
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium
    )
}