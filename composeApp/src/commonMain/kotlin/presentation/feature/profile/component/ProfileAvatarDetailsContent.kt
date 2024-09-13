package presentation.feature.profile.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import presentation.feature.profile.SharedElementProfileAvatar
import presentation.theme.Theme


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ProfileAvatarDetailsContent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Column(
        modifier = modifier
    ) {
        with(sharedTransitionScope) {
            ProfileAvatar(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = SharedElementProfileAvatar.Image),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .size(Theme.dimens.space200)
                    .clickable { onBack() }
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(Theme.dimens.space8))
            Text(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = SharedElementProfileAvatar.Name),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .fillMaxWidth(),
                text = "Full name: Volha Bakhur",
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(Theme.dimens.space8))
            Text(
                modifier = Modifier
                    .sharedElement(
                        state = rememberSharedContentState(key = SharedElementProfileAvatar.Age),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .fillMaxWidth(),
                text = "Age: 30",
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(Theme.dimens.space8))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                text = "About myself: Android developer",
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Start
            )
        }
    }
}