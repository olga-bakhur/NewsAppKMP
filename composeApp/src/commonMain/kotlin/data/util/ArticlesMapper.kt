package data.util

import data.model.dto.Article
import data.model.dto.ArticleList
import data.model.response.ArticleDetailSearchResponse
import data.model.response.ArticleListSearchResponse
import data.model.response.ArticleResponse

fun ArticleListSearchResponse.toArticleList(): ArticleList =
    with(response) {
        ArticleList(
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
    sectionId = categoryId,
    category = category,
    publicationDate = toEpochMillis(publicationDate),
    title = title,
    webUrl = webUrl,
    apiUrl = apiUrl,
    isHosted = isHosted,
    pillarId = pillarId,
    pillarName = pillarName,
    trailText = fields.trailText,
    bodyText = fields.bodyText,
    byline = fields.byline,
    lastModified = toEpochMillis(fields.lastModified),
    source = fields.source,
    thumbnail = fields.thumbnail
)