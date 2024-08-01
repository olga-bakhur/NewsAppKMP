package data.util

import data.model.dto.Article
import data.model.response.ArticleDetailSearchResponse
import data.model.response.ArticleListSearchResponse
import data.model.response.ArticleResponse

fun ArticleListSearchResponse.toArticleList(): List<Article> =
    response.results.map { articleResponse ->
        articleResponse.toArticle()
    }

fun ArticleDetailSearchResponse.toArticle(): Article =
    response.content.toArticle()


fun ArticleResponse.toArticle(): Article = Article(
    id = id,
    type = type,
    sectionId = sectionId,
    sectionName = sectionName,
    webPublicationDate = webPublicationDate,
    webTitle = webTitle,
    webUrl = webUrl,
    apiUrl = apiUrl,
    isHosted = isHosted,
    pillarId = pillarId,
    pillarName = pillarName
)