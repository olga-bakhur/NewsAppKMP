package data.source.remote

import data.base.result.ApiResult
import data.model.response.ArticleDetailSearchResponse
import data.model.response.ArticleListSearchResponse
import data.util.Config.API_KEY_ARTICLES
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface ArticlesApi {

    @GET("search")
    suspend fun fetchArticleList(
        @Query("api-key") apiKey: String = API_KEY_ARTICLES
    ): ApiResult<ArticleListSearchResponse>

    @GET("{id}")
    suspend fun fetchArticleDetailById(
        @Query("api-key") apiKey: String = API_KEY_ARTICLES,
        @Path("id") articleId: String
    ): ApiResult<ArticleDetailSearchResponse>
}