package data.source.remote

import data.base.result.ApiResult
import data.model.response.articledetail.ArticleDetailSearchResponse
import data.model.response.feed.FeedSearchResponse
import data.model.response.section.SectionsSearchResponse
import data.source.remote.ApiConfig.API_KEY_ARTICLES
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query

interface ArticlesApi {

    @GET("search")
    suspend fun fetchFeed(
        @Query("api-key") apiKey: String = API_KEY_ARTICLES,
        @Query("section") sectionId: String? = null,
        @Query("from-date") fromDate: String? = null,
        @Query("to-date") toDate: String? = null,
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("show-fields") fields: String = FieldsRequestBuilder.constructFeedRequestFields()
    ): ApiResult<FeedSearchResponse>

    @GET("{id}")
    suspend fun fetchArticleDetailById(
        @Query("api-key") apiKey: String = API_KEY_ARTICLES,
        @Query("show-fields") fields: String = FieldsRequestBuilder.constructArticleDetailRequestFields(),
        @Path("id") articleId: String
    ): ApiResult<ArticleDetailSearchResponse>

    @GET("sections")
    suspend fun fetchSections(
        @Query("api-key") apiKey: String = API_KEY_ARTICLES
    ): ApiResult<SectionsSearchResponse>
}