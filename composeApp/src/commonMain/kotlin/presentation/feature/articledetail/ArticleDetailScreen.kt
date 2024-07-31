package presentation.feature.articledetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import common.EMPTY
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.error
import newsappkmp.composeapp.generated.resources.no_info
import newsappkmp.composeapp.generated.resources.ok
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.navigation.Screen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ArticleDetailScreen(
    navController: NavController,
    articleUrl: String,
    viewModel: ArticleDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    val url = Screen.ArticleDetail.decodeUrl(articleUrl)

    LaunchedEffect(Unit) {
        if (url.isNotBlank()) {
            viewModel.getArticleByUrl(url)
        }
    }

    ScreenContent(state = state)
}

@ExperimentalFoundationApi
@Composable
private fun ScreenContent(
    modifier: Modifier = Modifier,
    state: ArticleDetailState
) {

    val article = state.article

    if (article != null) {
        Column(
            modifier = modifier
                .border(
                    border = BorderStroke(1.dp, Color.Black),
                    RoundedCornerShape(10.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = article.title ?: EMPTY,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = article.content ?: EMPTY,
                color = Color.Black,
                fontWeight = FontWeight.Light
            )
        }

    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(Res.string.no_info),
                modifier = modifier
                    .align(Alignment.Center)
            )
        }
    }

    if (state.loading) {
        Box(
            modifier = modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = modifier
                    .align(Alignment.Center),
                color = Color.Red
            )
        }
    }

    state.error?.let { appError ->
        AlertDialog(
            onDismissRequest = {
                // TODO: dismiss
            },
            confirmButton = {
                Text(
                    text = stringResource(Res.string.ok)
                )
            },
            title = {
                Text(
                    text = stringResource(Res.string.error)
                )
            },
            text = {
                Text(
                    text = stringResource(appError.messageRes)
                )
            }
        )
    }
}