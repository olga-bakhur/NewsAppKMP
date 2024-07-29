package presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.usecase.GetTopHeadlinesUseCase
import kotlinx.coroutines.launch

class TopHeadlinesViewModel(
    private val getTopHeadlinesUseCase: GetTopHeadlinesUseCase
) : ViewModel() {

    fun getTopHeadlines() {
        viewModelScope.launch {
            val listHeadlines = getTopHeadlinesUseCase.getTopHeadlines()
            println(listHeadlines)
        }
    }
}