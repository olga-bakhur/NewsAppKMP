package presentation.navigation

sealed class Graph(val route: String) {
    data object NavigationBar : Graph("navigation_bar_graph")
}