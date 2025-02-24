package com.example.composeapp.ui.shelf.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.diolkaee.alexandria.R
import com.diolkaee.alexandria.business.book.Author
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.EXAMPLE_BOOKS
import com.example.composeapp.ui.common.AlexandriaPreview
import com.example.composeapp.ui.common.applyIf
import com.example.composeapp.ui.theme.AlexandriaTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

private sealed interface BookListItem {
    data class BookEntry(val book: Book) : BookListItem
    data class AuthorEntry(val author: Author) : BookListItem
}

@Composable
fun ShelfList(
    books: List<Book>,
    onBookClick: (Book) -> Unit,
    onScrollToIndex: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    val bookListItems = remember(books) {
        books.groupBy { it.author }
            .flatMap { (author, books) ->
                listOf(BookListItem.AuthorEntry(author)) + books.map { BookListItem.BookEntry(it) }
            }
    }

    LaunchedEffect(listState, bookListItems) {
        snapshotFlow { listState.firstVisibleItemIndex }
            // Count the number of books in the list up to the current index emitting authors
            .map { index ->
                bookListItems
                    .take(index + 1)
                    .filterIsInstance<BookListItem.BookEntry>()
                    .lastIndex
            }
            .distinctUntilChanged()
            .collect(onScrollToIndex)
    }

    LazyColumn(modifier = modifier, state = listState) {
        itemsIndexed(bookListItems) { index, item ->
            when (item) {
                is BookListItem.BookEntry -> BookItem(
                    modifier = Modifier
                        .applyIf(index != bookListItems.lastIndex) { padding(bottom = 8.dp) },
                    book = item.book,
                    onClick = { onBookClick(item.book) },
                )

                is BookListItem.AuthorEntry -> AuthorItem(author = item.author)
            }
            if (index == bookListItems.lastIndex) {
                Spacer(Modifier.height(400.dp))
            }
        }
    }
}

@Composable
private fun AuthorItem(author: Author, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.fillMaxWidth(),
        text = author.toString(),
        style = MaterialTheme.typography.labelMedium,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

@Composable
private fun BookItem(
    book: Book,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        onClick = onClick,
    ) {
        Text(
            text = book.title,
            style = MaterialTheme.typography.labelLarge,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Spacer(Modifier.weight(1f))
        if (book.marked) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = stringResource(R.string.shelf_list_marked),
            )
        }
    }
}

@AlexandriaPreview
@Composable
private fun Preview() {
    AlexandriaTheme {
        ShelfList(books = EXAMPLE_BOOKS, onBookClick = {}, onScrollToIndex = {})
    }
}
