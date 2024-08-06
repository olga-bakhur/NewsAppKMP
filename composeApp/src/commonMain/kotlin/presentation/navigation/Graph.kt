package presentation.navigation

sealed class Graph(val route: String) {
    data object BottomBar : Graph("bottom_bar_graph")
}