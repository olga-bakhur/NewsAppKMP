package presentation.navigation.navbar

import androidx.compose.ui.graphics.vector.ImageVector

data class TopAppBarAction(
    val icon: ImageVector,
    val contentDescription: String,
    val onActionClicked: () -> Unit
)