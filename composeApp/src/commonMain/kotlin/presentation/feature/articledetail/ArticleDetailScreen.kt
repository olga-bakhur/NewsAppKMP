package presentation.feature.articledetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import common.EMPTY
import data.util.millisToFormattedDateString
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.no_info
import newsappkmp.composeapp.generated.resources.save
import newsappkmp.composeapp.generated.resources.screen_title_article_detail
import newsappkmp.composeapp.generated.resources.share
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.BaseErrorDialog
import presentation.component.LoadImageFromUrl
import presentation.navigation.Screen
import presentation.navigation.navbar.TopAppBar
import presentation.navigation.navbar.TopAppBarActionItem

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    viewModel: ArticleDetailViewModel = koinViewModel(),
    articleId: String,
    onBackClicked: () -> Unit
) {
    val decodedArticleId = Screen.ArticleDetail.decodeUrl(articleId)

    LaunchedEffect(Unit) {
        if (articleId.isNotBlank()) {
            viewModel.fetchArticleDetailById(decodedArticleId)
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    ScreenContent(
        state = state,
        dismissError = viewModel::dismissError,
        scrollBehavior = scrollBehavior,
        onBackClicked = onBackClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalFoundationApi
@Composable
private fun ScreenContent(
    state: ArticleDetailState,
    dismissError: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClicked: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = stringResource(Res.string.screen_title_article_detail),
                maxLines = 1,
                scrollBehavior = scrollBehavior,
                isTopLevelDestination = false,
                onBackClicked = {
                    onBackClicked.invoke()
                },
                actions = listOf(
                    TopAppBarActionItem(
                        icon = Icons.Outlined.Star,
                        contentDescription = stringResource(Res.string.save),
                        onActionClicked = {
                            println("Save clicked")
                        }
                    ),
                    TopAppBarActionItem(
                        icon = Icons.Outlined.Share,
                        contentDescription = stringResource(Res.string.share),
                        onActionClicked = {
                            println("Share clicked")
                        }
                    )
                )
            )
        }
    ) { paddingValues ->
        val article = state.article

        if (article != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(state = rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Thumbnail
                LoadImageFromUrl(
                    imageUri = article.thumbnail,
                    contentDescription = "Thumbnail",
                    widthInPx = 500,
                    heightInPx = 300
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = article.title,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Body text
                Text(
                    text = article.bodyText ?: EMPTY,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )

                Spacer(modifier = Modifier.height(8.dp))

                // By line
                Text(
                    text = article.byline ?: EMPTY,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Source
                Text(
                    text = article.source,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Category
                Text(
                    text = article.sectionName,
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Date
                Text(
                    text = millisToFormattedDateString(article.publicationDate),
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Last modified
                Text(
                    text = millisToFormattedDateString(article.lastModified),
                    color = Color.Black,
                    fontWeight = FontWeight.Light
                )

                Spacer(modifier = Modifier.height(8.dp))

            }
        } else {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Text(
                    text = stringResource(Res.string.no_info),
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

        if (state.loading) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.Red
                )
            }
        }

        // Errors
        if (state.errors.isNotEmpty()) {
            BaseErrorDialog(
                error = state.errors.first(),
                onDismiss = { dismissError.invoke() }
            )
        }
    }
}