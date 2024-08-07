package presentation.component


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun BaseFloatingActionButton(
    imageVector: ImageVector,
    contentDescription: String,
    shape: Shape = CircleShape,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick.invoke() },
        shape = shape
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}