package data.util

import data.model.dto.Article
import data.model.response.TopHeadlinesResponse

fun TopHeadlinesResponse.toArticleList(): List<Article> =
    articles?.map { articleResponse ->
        Article(
            sourceId = articleResponse.source?.id,
            sourceName = articleResponse.source?.name,
            author = articleResponse.author,
            title = articleResponse.title,
            description = articleResponse.description,
            url = articleResponse.url,
            urlToImage = articleResponse.urlToImage,
            publishedAt = articleResponse.publishedAt,
            content = articleResponse.content
        )
    } ?: emptyList()


