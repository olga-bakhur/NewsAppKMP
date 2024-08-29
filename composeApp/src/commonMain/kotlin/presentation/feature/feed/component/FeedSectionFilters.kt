package presentation.feature.feed.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ContextualFlowRowOverflowScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import data.model.dto.Section
import presentation.component.BaseFilterChip
import presentation.component.OverflowFilterChip
import presentation.theme.Theme
import kotlin.reflect.KFunction1

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FeedSectionFilters(
    modifier: Modifier = Modifier,
    sections: List<Section>,
    onFilterSelected: KFunction1<String?, Unit>,
    currentSectionId: String?
) {
    var maxLines by rememberSaveable() { mutableStateOf(2) }

    val moreOrCollapseIndicator = @Composable { scope: ContextualFlowRowOverflowScope ->
        val remainingItems = scope.totalItemCount - scope.shownItemCount
        OverflowFilterChip(
            remainingItems = remainingItems,
            maxLines = maxLines,
            onMaxLinesChange = { maxLines = it },
            fontFamily = FontFamily.Cursive,
            textStyle = MaterialTheme.typography.titleMedium
        )
    }

    ContextualFlowRow(
        modifier = modifier.animateContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalArrangement = Arrangement.spacedBy(
            space = Theme.dimens.space4,
            alignment = Alignment.CenterHorizontally
        ),
        maxLines = maxLines,
        overflow = ContextualFlowRowOverflow.expandOrCollapseIndicator(
            minRowsToShowCollapse = 4,
            expandIndicator = moreOrCollapseIndicator,
            collapseIndicator = moreOrCollapseIndicator
        ),
        itemCount = sections.size
    ) { index ->
        val section = sections[index]

        BaseFilterChip(
            label = section.sectionName,
            fontFamily = FontFamily.Cursive,
            textStyle = MaterialTheme.typography.titleMedium,
            icon = Icons.Filled.Done,
            onChipClicked = {
                onFilterSelected.invoke(section.sectionId)
            },
            selected = currentSectionId == section.sectionId
        )
    }
}