package data.base.error

import data.base.result.ApiError
import data.base.result.ApiException
import data.base.result.ApiResult
import data.base.result.ApiSuccess
import data.base.result.Result


fun <T : Any> handleError(result: ApiResult<T>): Result.Error<T> {
    return when (result) {
        is ApiError -> Result.Error(AppError.AuthError.findByCode(result.errorBody?.code))
        is ApiException -> Result.Error(AppError.NetworkError())
        is ApiSuccess -> throw IllegalArgumentException("ApiSuccess should not be handled in handleError")
    }
}
