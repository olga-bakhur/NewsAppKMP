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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import common.EMPTY
import data.util.millisToFormattedDateString
import newsappkmp.composeapp.generated.resources.Res
import newsappkmp.composeapp.generated.resources.by_line
import newsappkmp.composeapp.generated.resources.last_modified
import newsappkmp.composeapp.generated.resources.no_info
import newsappkmp.composeapp.generated.resources.published
import newsappkmp.composeapp.generated.resources.save
import newsappkmp.composeapp.generated.resources.screen_title_article_detail
import newsappkmp.composeapp.generated.resources.section
import newsappkmp.composeapp.generated.resources.share
import newsappkmp.composeapp.generated.resources.source
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import presentation.component.BaseErrorDialog
import presentation.component.LoadImageFromUrl
import presentation.navigation.Screen
import presentation.navigation.navbar.TopAppBar
import presentation.navigation.navbar.TopAppBarActionItem
import presentation.theme.Theme

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

                Thumbnail(imageUri = article.thumbnail)
                Spacer(modifier = Modifier.height(Theme.dimens.space8))

                Title(title = article.title)
                Spacer(modifier = Modifier.height(Theme.dimens.space8))

                BodyText(bodyText = article.bodyText ?: EMPTY)
                Spacer(modifier = Modifier.height(Theme.dimens.space16))

                ByLine(byLine = article.byline ?: EMPTY)
                Spacer(modifier = Modifier.height(Theme.dimens.space4))

                Source(source = article.source)
                Spacer(modifier = Modifier.height(Theme.dimens.space4))

                Section(section = article.sectionName)
                Spacer(modifier = Modifier.height(Theme.dimens.space4))

                PublicationDate(publicationDate = millisToFormattedDateString(article.publicationDate))
                Spacer(modifier = Modifier.height(Theme.dimens.space4))

                LastModified(lastModified = millisToFormattedDateString(article.lastModified))
                Spacer(modifier = Modifier.height(Theme.dimens.space16))
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                EmptyState(modifier = Modifier.align(Alignment.Center))
            }
        }

        // Loading
        if (state.loading) {
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
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

@Composable
fun Thumbnail(
    imageUri: String? = null
) {
    LoadImageFromUrl(
        imageUri = imageUri,
        contentDescription = "Thumbnail"
    )
}

@Composable
fun Title(
    title: String
) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
fun BodyText(
    bodyText: String
) {
    Text(
        text = bodyText,
        fontWeight = FontWeight.Normal,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
fun ByLine(
    byLine: String
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(Res.string.by_line))
            }

            append(byLine)
        },
        fontWeight = FontWeight.Light,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun Source(
    source: String
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(Res.string.source))
            }

            append(source)
        },
        fontWeight = FontWeight.Light,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun Section(
    section: String
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(Res.string.section))
            }

            append(section)
        },
        fontWeight = FontWeight.Light,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun PublicationDate(
    publicationDate: String
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(Res.string.published))
            }

            append(publicationDate)
        },
        fontWeight = FontWeight.Light,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun LastModified(
    lastModified: String
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append(stringResource(Res.string.last_modified))
            }

            append(lastModified)
        },
        fontWeight = FontWeight.Light,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun EmptyState(
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(Res.string.no_info),
        modifier = modifier
    )
}