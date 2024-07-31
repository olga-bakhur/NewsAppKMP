package data.util

import data.model.dto.Article
import data.model.response.ArticleResponse
import data.model.response.TopHeadlinesResponse

fun TopHeadlinesResponse.toArticleList(): List<Article> =
    articles?.map { articleResponse ->
        articleResponse.toArticle()
    } ?: emptyList()

fun ArticleResponse.toArticle(): Article = Article(
    sourceId = source?.id,
    sourceName = source?.name,
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt,
    content = content
)