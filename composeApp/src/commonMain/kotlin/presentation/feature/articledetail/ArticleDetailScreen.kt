package presentation.feature.articledetail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import common.EMPTY
import data.util.millisToFormattedDateString
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.save
import newsappkmp.composeapp.generated.resources.screen_title_article_detail
import newsappkmp.composeapp.generated.resources.share
import org.jetbrains.compose.resources.stringResource
import presentation.component.BaseErrorDialog
import presentation.component.BaseLoading
import presentation.feature.articledetail.component.ArticleDetailBodyText
import presentation.feature.articledetail.component.ArticleDetailByLine
import presentation.feature.articledetail.component.ArticleDetailEmptyState
import presentation.feature.articledetail.component.ArticleDetailLastModified
import presentation.feature.articledetail.component.ArticleDetailPublicationDate
import presentation.feature.articledetail.component.ArticleDetailSection
import presentation.feature.articledetail.component.ArticleDetailSource
import presentation.feature.articledetail.component.ArticleDetailThumbnail
import presentation.feature.articledetail.component.ArticleDetailTitle
import presentation.navigation.Screen
import presentation.navigation.navbar.TopAppBar
import presentation.navigation.navbar.TopAppBarActionItem
import presentation.theme.Theme

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalSharedTransitionApi::class
)
@Composable
fun ArticleDetailScreen(
    viewModel: ArticleDetailViewModel,
    articleId: String,
    onBackClicked: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
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
        onBackClicked = onBackClicked,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@ExperimentalFoundationApi
@Composable
private fun ScreenContent(
    state: ArticleDetailState,
    dismissError: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClicked: () -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = stringResource(Res.string.screen_title_article_detail),
                maxLines = 1,
                fontFamily = FontFamily.Cursive,
                textStyle = MaterialTheme.typography.headlineMedium,
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
                    .padding(horizontal = Theme.dimens.space16)
                    .verticalScroll(state = rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(Theme.dimens.space8))

                ArticleDetailThumbnail(
                    imageUri = article.thumbnail,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope
                )
                Spacer(modifier = Modifier.height(Theme.dimens.space8))

                ArticleDetailTitle(
                    title = article.title,
                    articleId = article.articleId,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope
                )
                Spacer(modifier = Modifier.height(Theme.dimens.space8))

                ArticleDetailBodyText(bodyText = article.bodyText ?: EMPTY)
                Spacer(modifier = Modifier.height(Theme.dimens.space16))

                ArticleDetailByLine(byLine = article.byline ?: EMPTY)
                Spacer(modifier = Modifier.height(Theme.dimens.space4))

                ArticleDetailSource(source = article.source)
                Spacer(modifier = Modifier.height(Theme.dimens.space4))

                ArticleDetailSection(section = article.sectionName)
                Spacer(modifier = Modifier.height(Theme.dimens.space4))

                ArticleDetailPublicationDate(publicationDate = millisToFormattedDateString(article.publicationDate))
                Spacer(modifier = Modifier.height(Theme.dimens.space4))

                ArticleDetailLastModified(lastModified = millisToFormattedDateString(article.lastModified))
                Spacer(modifier = Modifier.height(Theme.dimens.space16))
            }
        } else {
            ArticleDetailEmptyState(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            )
        }

        // Loading
        BaseLoading(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            isLoading = state.loading
        )

        // Errors
        BaseErrorDialog(
            isError = state.errors.isNotEmpty(),
            error = state.errors.firstOrNull(),
            onDismiss = { dismissError.invoke() }
        )
    }
}
