package di

import data.base.result.AppResponseConverterFactory
import data.repository.NewsRepositoryImpl
import data.source.remote.NewsApi
import data.source.remote.createNewsApi
import data.util.Config.BASE_URL_NEWS
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.ktorfit
import domain.repository.NewsRepository
import domain.usecase.GetArticleUseCase
import domain.usecase.GetTopHeadlinesUseCase
import domain.util.AppDispatchers
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
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
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import presentation.feature.articledetail.ArticleDetailViewModel
import presentation.feature.topheadlines.TopHeadlinesViewModel

expect val platformModule: Module

val sharedModule = module {
    /* Networking */
    singleOf(::provideHttpClient)
    singleOf(::provideJson)
    singleOf(::provideKtorfitClient)
    singleOf(::provideApi)
    singleOf(::provideAppDispatchers)

    /* Repository */
    singleOf(::NewsRepositoryImpl).bind<NewsRepository>()

    /* UseCase */
    factoryOf(::GetTopHeadlinesUseCase)
    factoryOf(::GetArticleUseCase)

    /* ViewModel */
    viewModelOf(::TopHeadlinesViewModel)
    viewModelOf(::ArticleDetailViewModel)
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
    }

fun provideJson(): Json = Json {
    isLenient = true
    ignoreUnknownKeys = true
}

fun provideKtorfitClient(httpClient: HttpClient): Ktorfit =
    ktorfit {
        baseUrl(BASE_URL_NEWS)
        httpClient(httpClient)
        converterFactories(
            AppResponseConverterFactory(),
        )
    }

fun provideApi(
    ktorfit: Ktorfit,
): NewsApi = ktorfit.createNewsApi()

fun provideAppDispatchers(): AppDispatchers = AppDispatchers(
    ui = Dispatchers.Main,
    default = Dispatchers.Default,
    io = Dispatchers.IO,
    unconfined = Dispatchers.Unconfined
)
