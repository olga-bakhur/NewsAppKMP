package presentation.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import presentation.theme.Theme

@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    elevation: Dp = Theme.dimens.space6,
    onCardClicked: (() -> Unit)? = null,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
        onClick = {
            onCardClicked?.invoke()
        },
        content = content
    )
}