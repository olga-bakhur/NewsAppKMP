package data.model.mapper

import data.model.dto.Article
import data.model.dto.Feed
import data.model.response.articledetail.ArticleDetailSearchResponse
import data.model.response.articledetail.ArticleResponse
import data.model.response.feed.FeedSearchResponse
import data.util.isoFormatToMillis
import data.util.removeHtmlTags

fun FeedSearchResponse.toFeed(): Feed =
    with(response) {
        Feed(
            totalArticlesCount = totalArticlesCount,
            startIndex = startIndex,
            pageSize = pageSize,
            currentPage = currentPage,
            totalPagesCount = totalPagesCount,
            orderBy = orderBy,
            articles = articles.map { articleResponse ->
                articleResponse.toArticle()
            }
        )
    }

fun ArticleDetailSearchResponse.toArticle(): Article =
    response.article.toArticle()


fun ArticleResponse.toArticle(): Article = Article(
    articleId = articleId,
    type = type,
    sectionId = sectionId,
    sectionName = sectionName,
    publicationDate = isoFormatToMillis(publicationDate),
    title = title,
    webUrl = webUrl,
    apiUrl = apiUrl,
    isHosted = isHosted,
    pillarId = pillarId,
    pillarName = pillarName,
    trailText = fields.trailText.removeHtmlTags(),
    bodyText = fields.bodyText,
    byline = fields.byline,
    lastModified = isoFormatToMillis(fields.lastModified),
    source = fields.source,
    thumbnail = fields.thumbnail
)