package presentation.feature.favorite

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import presentation.base.BaseViewModel

class FavoriteViewModel : BaseViewModel() {

    private val sampleFlow: StateFlow<Int>
        field = MutableStateFlow(0)
}