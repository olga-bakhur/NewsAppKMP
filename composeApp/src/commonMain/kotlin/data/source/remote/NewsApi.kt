package data.source.remote

import data.base.result.ApiResult
import data.model.response.TopHeadlinesResponse
import data.util.Config.API_KEY_NEWS
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface NewsApi {

    @GET("/top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String = "bbc-news",
        @Query("apiKey") apiKey: String = API_KEY_NEWS
    ): ApiResult<TopHeadlinesResponse>
}