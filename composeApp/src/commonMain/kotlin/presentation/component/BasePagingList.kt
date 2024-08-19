package presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems
import presentation.theme.Theme


@Composable
fun <T : Any> BasePagingList(
    modifier: Modifier = Modifier,
    data: LazyPagingItems<T>,
    paddingValues: PaddingValues = PaddingValues(16.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    contentTop: @Composable() (LazyListScope.() -> Unit)? = null,
    bottomPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable (T?, Modifier) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Theme.colors.background),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = paddingValues
    ) {
        item {
            contentTop?.invoke(this@LazyColumn)
        }

        items(data.itemCount) { index ->
            val item = data[index]
            // Check if this is the last index
            if (index == data.itemCount - 1) {
                // This is the last index, you can add padding or any other logic here
                content(item, Modifier.padding(bottomPadding))
            } else {
                content(item, Modifier)
            }
        }

        data.loadState.apply {
            when {
                refresh is LoadStateNotLoading && data.itemCount < 1 -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No Items",
                                modifier = Modifier.align(Alignment.Center),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                refresh is LoadStateLoading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Theme.colors.primary,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }

                append is LoadStateLoading -> {
                    item {
                        CircularProgressIndicator(
                            color = Theme.colors.primary,
                            modifier = Modifier.fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }

                refresh is LoadStateError -> {
                    item {
                        ErrorView(
                            message = "No Internet Connection",
                            onClickRetry = { data.retry() },
                            modifier = Modifier.fillParentMaxSize()
                        )
                    }
                }

                append is LoadStateError -> {
                    item {
                        ErrorItem(
                            message = "No Internet Connection",
                            onClickRetry = { data.retry() },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = Theme.typography.body,
            color = Color.Red
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Try again")
        }
    }
}

@Composable
private fun ErrorView(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit,
) {
    Column(
        modifier = modifier.padding(16.dp).onPlaced { cor ->

        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = Theme.typography.body,
            color = Color.Red
        )
        BaseOutlinedButton(
            title = "Try again",
            onClick = onClickRetry,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}