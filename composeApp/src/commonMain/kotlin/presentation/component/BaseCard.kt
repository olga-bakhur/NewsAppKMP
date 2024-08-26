package presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import presentation.theme.Theme

@Composable
fun BaseCard(
    modifier: Modifier = Modifier,
    contentPadding: Dp = Theme.dimens.space16,
    elevation: Dp = Theme.dimens.space6,
    onCardClicked: (() -> Unit)? = null,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
        onClick = {
            onCardClicked?.invoke()
        },
        content = {
            Column(
                modifier = modifier.padding(contentPadding)
            ) {
                content.invoke(this@ElevatedCard)
            }
        }
    )
}