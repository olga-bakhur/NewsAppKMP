package presentation.feature.profile.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class)
@Composable
fun CollapsingAvatar(
    showDetails: Boolean,
    onShowDetailsChanged: (shouldShowDetails: Boolean) -> Unit
) {
    val boundsAnimationDurationMillis = 1000
    val boundsTransform = BoundsTransform { initialBounds, targetBounds ->
        keyframes {
            durationMillis = boundsAnimationDurationMillis
            initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
            targetBounds at boundsAnimationDurationMillis
        }
    }

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
                    boundsTransform = boundsTransform,
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            } else {
                ProfileAvatarDetailsContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    boundsTransform = boundsTransform,
                    onBack = { onShowDetailsChanged(false) },
                    animatedVisibilityScope = this@AnimatedContent,
                    sharedTransitionScope = this@SharedTransitionLayout
                )
            }
        }
    }
}