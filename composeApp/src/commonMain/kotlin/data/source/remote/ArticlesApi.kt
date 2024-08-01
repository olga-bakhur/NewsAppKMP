package data.source.remote

import data.base.result.ApiResult
import data.model.response.ArticleDetailSearchResponse
import data.model.response.ArticleListSearchResponse
import data.util.Config.API_KEY_ARTICLES
import data.util.FieldsRequestBuilder
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface ArticlesApi {

    @GET("search")
    suspend fun fetchArticleList(
        @Query("api-key") apiKey: String = API_KEY_ARTICLES,
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-fields") fields: String = FieldsRequestBuilder.constructArticleListRequestFields()
    ): ApiResult<ArticleListSearchResponse>

    @GET("{id}")
    suspend fun fetchArticleDetailById(
        @Query("api-key") apiKey: String = API_KEY_ARTICLES,
        @Query("show-fields") fields: String = FieldsRequestBuilder.constructArticleDetailRequestFields(),
        @Path("id") articleId: String
    ): ApiResult<ArticleDetailSearchResponse>
}