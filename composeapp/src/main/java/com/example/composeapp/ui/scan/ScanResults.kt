package com.example.composeapp.ui.scan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.EXAMPLE_BOOKS
import com.example.composeapp.ui.theme.AlexandriaTheme

@Composable
fun ScanResults(
    books: List<Book>,
    onArchiveBook: (Book) -> Unit,
    modifier: Modifier = Modifier,
) {
    val newestBooksFirst by remember(books) { derivedStateOf { books.reversed() } }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(
            items = newestBooksFirst,
            key = { it.isbn.hashCode() * 31 + it.publisher.hashCode() },
        ) {
            ScanResult(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(),
                book = it,
                onClick = { onArchiveBook(it) },
            )
        }
    }
}

@Composable
private fun ScanResult(
    book: Book,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.background(Color.Transparent),
        onClick = onClick,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column {
                    Text(
                        text = book.title,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Black,
                    )
                    Text(
                        book.author.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                    )
                }
                AsyncImage(
                    modifier = Modifier.size(width = 40.dp, height = 60.dp),
                    model = book.thumbnailUrl,
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
@Preview
private fun Preview() {
    AlexandriaTheme {
        ScanResults(
            books = EXAMPLE_BOOKS,
            onArchiveBook = {},
        )
    }
}