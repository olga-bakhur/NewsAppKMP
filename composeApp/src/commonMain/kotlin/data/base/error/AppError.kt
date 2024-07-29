package data.base.error

import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.error_email_already_exists
import newsappkmp.composeapp.generated.resources.error_email_not_found
import newsappkmp.composeapp.generated.resources.error_invalid_access_token
import newsappkmp.composeapp.generated.resources.error_invalid_email
import newsappkmp.composeapp.generated.resources.error_invalid_password
import newsappkmp.composeapp.generated.resources.error_invalid_username
import newsappkmp.composeapp.generated.resources.error_network
import newsappkmp.composeapp.generated.resources.error_unknown
import newsappkmp.composeapp.generated.resources.error_username_unavailable
import newsappkmp.composeapp.generated.resources.error_wrong_credentials
import org.jetbrains.compose.resources.StringResource

sealed class AppError(val messageRes: StringResource) {

    class NetworkError(messageRes: StringResource = Res.string.error_network) : AppError(messageRes)

    class UnknownError(messageRes: StringResource = Res.string.error_unknown) : AppError(messageRes)

    sealed class AuthError(val code: String, messageRes: StringResource) : AppError(messageRes) {
        data object WrongCredentials :
            AuthError("wrong_credentials", Res.string.error_wrong_credentials)

        data object InvalidUsername :
            AuthError("invalid_username", Res.string.error_invalid_username)

        data object InvalidEmail : AuthError("invalid_email", Res.string.error_invalid_email)
        data object InvalidPassword :
            AuthError("invalid_password", Res.string.error_invalid_password)

        data object InvalidToken :
            AuthError("invalid_token", Res.string.error_invalid_access_token)

        data object UnavailableUsername : AuthError(
            "unavailable_username",
            Res.string.error_username_unavailable
        )

        data object EmailInUse : AuthError("email_in_use", Res.string.error_email_already_exists)
        data object EmailNotExist : AuthError("email_not_exist", Res.string.error_email_not_found)

        companion object {
            private fun values() = listOf(
                WrongCredentials,
                InvalidUsername,
                InvalidEmail,
                InvalidPassword,
                InvalidToken,
                UnavailableUsername,
                EmailInUse,
                EmailNotExist
            )

            fun findByCode(code: String?): AppError {
                return values().find { it.code == code } ?: UnknownError()
            }
        }
    }

    sealed class LocalStorageAppError(messageRes: StringResource) : AppError(messageRes) {
        data object AuthTokenNotFound : LocalStorageAppError(Res.string.error_invalid_access_token)
    }
}
