package di

import data.base.result.AppResponseConverterFactory
import data.repository.ArticlesRepositoryImpl
import data.source.remote.ArticlesApi
import data.source.remote.createArticlesApi
import data.source.remote.paging.ArticlesPagingSource
import data.util.Config.BASE_URL_ARTICLES
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.ktorfit
import domain.repository.ArticlesRepository
import domain.usecase.FetchArticleDetailUseCase
import domain.usecase.FetchArticleListUseCase
import domain.util.AppDispatchers
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.feature.articledetail.ArticleDetailViewModel
import presentation.feature.feed.FeedViewModel
import presentation.feature.favorite.FavoriteViewModel
import presentation.feature.settings.SettingsViewModel

private const val NETWORK_TIME_OUT = 10_000L

expect val platformModule: Module

val sharedModule = module {
    /* Networking */
    singleOf(::provideHttpClient)
    singleOf(::provideJson)
    singleOf(::provideKtorfitClient)
    singleOf(::provideApi)
    singleOf(::provideAppDispatchers)

    /* Repository */
    singleOf(::ArticlesRepositoryImpl).bind<ArticlesRepository>()

    /* UseCase */
    singleOf(::FetchArticleListUseCase)
    singleOf(::FetchArticleDetailUseCase)

    /* ViewModel */
    viewModelOf(::FeedViewModel)
    viewModelOf(::ArticleDetailViewModel)
    viewModelOf(::FavoriteViewModel)
    viewModelOf(::SettingsViewModel)

    /* Paging */
    singleOf(::ArticlesPagingSource)
}


fun provideHttpClient(json: Json): HttpClient =
    HttpClient {
        install(ContentNegotiation) {
            json(json)
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            requestTimeoutMillis = NETWORK_TIME_OUT
            connectTimeoutMillis = NETWORK_TIME_OUT
            socketTimeoutMillis = NETWORK_TIME_OUT
        }
    }

fun provideJson(): Json = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

fun provideKtorfitClient(httpClient: HttpClient): Ktorfit =
    ktorfit {
        baseUrl(BASE_URL_ARTICLES)
        httpClient(httpClient)
        converterFactories(
            AppResponseConverterFactory(),
        )
    }

fun provideApi(
    ktorfit: Ktorfit,
): ArticlesApi = ktorfit.createArticlesApi()

fun provideAppDispatchers(): AppDispatchers = AppDispatchers(
    ui = Dispatchers.Main,
    default = Dispatchers.Default,
    io = Dispatchers.IO,
    unconfined = Dispatchers.Unconfined
)