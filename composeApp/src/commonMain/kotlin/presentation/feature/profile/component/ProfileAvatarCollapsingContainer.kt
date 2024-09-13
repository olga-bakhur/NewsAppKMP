package presentation.feature.profile.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CollapsingAvatar(
    showDetails: Boolean,
    onShowDetailsChanged: (shouldShowDetails: Boolean) -> Unit
) {
    SharedTransitionLayout {
        AnimatedContent(
            targetState = showDetails,
            label = "basic_transition"
        ) { targetState ->
            if (!targetState) {
                ProfileAvatarMainContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onShowDetails = { onShowDetailsChanged(true) },
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            } else {
                ProfileAvatarDetailsContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    onBack = { onShowDetailsChanged(false) },
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }
        }
    }
}