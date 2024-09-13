package presentation.feature.profile.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import presentation.theme.Theme


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onShowDetails: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Row(
        modifier = modifier
    ) {
        with(sharedTransitionScope) {
            ProfileAvatar(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = "image"),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .size(Theme.dimens.space100)
                    .clickable { onShowDetails() }
                    .align(Alignment.CenterVertically)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = "userName"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    text = "Full name: Volha Bakhur",
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(Theme.dimens.space8))
                Text(
                    modifier = Modifier
                        .sharedElement(
                            state = rememberSharedContentState(key = "userAge"),
                            animatedVisibilityScope = animatedVisibilityScope
                        ),
                    text = "Age: 30",
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}