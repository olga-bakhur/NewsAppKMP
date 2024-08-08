package data.base.result

import data.base.error.ErrorBody
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.converter.KtorfitResult
import de.jensklingenberg.ktorfit.converter.TypeData
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer


class AppResponseConverterFactory : Converter.Factory {
    @OptIn(InternalSerializationApi::class)
    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        if (typeData.typeInfo.type == ApiResult::class) {
            return object : Converter.SuspendResponseConverter<HttpResponse, ApiResult<*>> {
                override suspend fun convert(result: KtorfitResult): ApiResult<*> {
                    return when (result) {
                        is KtorfitResult.Success -> handleApiResponse(
                            result.response,
                            typeData.typeArgs.first().typeInfo.type.serializer()
                        )

                        is KtorfitResult.Failure -> ApiException<Any>(result.throwable)
                    }
                }
            }
        }
        return null
    }
}

suspend fun <T : Any> handleApiResponse(
    response: HttpResponse,
    serializer: KSerializer<T>
): ApiResult<T> {
    return try {
        if (response.status.isSuccess()) {
            val json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            val data = json.decodeFromString(serializer, response.bodyAsText())
            println("KTOR / Success: $data")
            ApiSuccess(response.status.value, data)
        } else {
            val errorBody = Json.decodeFromString<ErrorBody>(response.bodyAsText())
            println("KTOR / Error: $errorBody")
            ApiError(response.status.value, errorBody)
        }
    } catch (e: Exception) {
        println("KTOR / exception: $e")
        ApiException(e)
    }
}
