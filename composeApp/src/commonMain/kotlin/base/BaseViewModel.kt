package base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.base.error.AppError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel() {

    protected open val _loading = MutableStateFlow(false)
    protected open val _errors = MutableStateFlow<List<AppError>>(emptyList())

    fun launchWithLoading(
        coroutineContext: CoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(coroutineContext) {
            _loading.emit(true)

            block.invoke(this)

            _loading.emit(false)
        }
    }

    fun dismissError() {
        _errors.value = _errors.value.drop(1)
    }

    fun addError(error: AppError) {
        _errors.value += error
    }
}