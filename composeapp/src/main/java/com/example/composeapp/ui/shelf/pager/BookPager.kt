package com.example.composeapp.ui.shelf.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.EXAMPLE_BOOKS
import com.example.composeapp.ui.theme.AlexandriaTheme
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookPager(
    books: List<Book>,
    pagerPosition: Int,
    onPagerPositionChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pagerState = rememberPagerState(pageCount = { books.size })

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.settledPage }.collect(onPagerPositionChange)
    }

    LaunchedEffect(pagerPosition) {
        pagerState.animateScrollToPage(pagerPosition)
    }

    HorizontalPager(
        modifier = modifier.background(Color.LightGray),
        state = pagerState,
        key = { books[it].isbn },
        userScrollEnabled = false,
        beyondViewportPageCount = 1,
        pageSize = PageSize.Fixed(200.dp),
        pageSpacing = 40.dp,
        contentPadding = PaddingValues(start = 80.dp, top = 40.dp, bottom = 40.dp),
    ) { page ->
        BookPage(
            modifier = Modifier
                .aspectRatio(2 / 3f)
                .height(300.dp)
                .graphicsLayer {
                    val pageOffset = (
                        (pagerState.currentPage - page) +
                            pagerState.currentPageOffsetFraction
                        ).absoluteValue

                    val alphaLerp = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f),
                    )

                    val scaleLerp = lerp(
                        start = 0.8f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f),
                    )

                    alpha = alphaLerp
                    scaleX = scaleLerp
                    scaleY = scaleLerp
                },
            book = books[page],
        )
    }
}

@Composable
private fun BookPage(book: Book, modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        if (book.thumbnailUrl != null) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = book.thumbnailUrl,
                contentDescription = book.title,
            )
        } else {
            DefaultBookPage(book = book)
        }
    }
}

@Composable
private fun DefaultBookPage(book: Book, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = book.author.toString(),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = book.title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AlexandriaTheme {
        BookPager(
            books = EXAMPLE_BOOKS,
            pagerPosition = 0,
            onPagerPositionChange = {},
        )
    }
}
