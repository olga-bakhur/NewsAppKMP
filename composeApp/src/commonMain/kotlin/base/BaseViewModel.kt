package base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.base.error.AppError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class BaseViewModel : ViewModel() {

    open val loading = MutableStateFlow(false)
    open val errors = MutableStateFlow<List<AppError>>(emptyList())

    fun launchWithLoading(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(coroutineContext) {
            loading.emit(true)

            block.invoke(this)

            loading.emit(false)
        }
    }

    fun dismissError() {
        errors.value = errors.value.drop(1)
    }

    fun addError(error: AppError) {
        errors.value += error
    }
}