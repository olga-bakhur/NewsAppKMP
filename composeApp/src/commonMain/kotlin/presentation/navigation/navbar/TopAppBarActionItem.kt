package presentation.navigation.navbar

import androidx.compose.ui.graphics.vector.ImageVector

data class TopAppBarActionItem(
    val icon: ImageVector,
    val contentDescription: String,
    val isSelected: Boolean = false,
    val onActionClicked: () -> Unit
)