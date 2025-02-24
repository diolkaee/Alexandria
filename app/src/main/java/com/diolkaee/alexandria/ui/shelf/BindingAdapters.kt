package com.diolkaee.alexandria.ui.shelf

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.diolkaee.alexandria.R
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.common.list.BookClickListener
import com.diolkaee.alexandria.ui.capture.ResultClickListener
import com.diolkaee.alexandria.ui.capture.SearchResult
import com.diolkaee.alexandria.ui.capture.SearchResultAdapter
import kotlin.math.abs

@BindingAdapter("books")
fun ViewPager2.setBooks(books: List<Book>) {
    val adapter = this.adapter as? BookPreviewAdapter ?: BookPreviewAdapter().also {
        it.setHasStableIds(true)
        this.adapter = it

        // Render adjacent books visible
        offscreenPageLimit = 1
        val multiPageTransformer = createMultiPageTransformer(resources)
        val shrinkTransformer = createShrinkTransformer()
        setPageTransformer(
            CompositePageTransformer().apply {
                addTransformer(multiPageTransformer)
                addTransformer(shrinkTransformer)
            },
        )

        val itemDecoration = HorizontalMarginDecoration(context, R.dimen.viewpager_book_margin)
        addItemDecoration(itemDecoration)
    }

    adapter.submitList(books)
}

private fun createMultiPageTransformer(resources: Resources): ViewPager2.PageTransformer {
    val nextVisibleBookPx = resources.getDimensionPixelOffset(R.dimen.viewpager_book_peek_size)
    val currentBookHorizontalMarginPx =
        resources.getDimensionPixelOffset(R.dimen.viewpager_book_margin)
    val pageTranslationX = 2 * nextVisibleBookPx + currentBookHorizontalMarginPx
    return ViewPager2.PageTransformer { page, position ->
        page.translationX = -pageTranslationX * position
    }
}

private fun createShrinkTransformer(): ViewPager2.PageTransformer =
    ViewPager2.PageTransformer { page, position ->
        val scale = if (position < 0) position / 4 + 1f else abs(1f - position / 4)
        page.scaleX = scale
        page.scaleY = scale
    }

private class HorizontalMarginDecoration(context: Context, @DimenRes horizontalMargin: Int) :
    RecyclerView.ItemDecoration() {
    private val horizontalMarginInPx: Int =
        context.resources.getDimensionPixelOffset(horizontalMargin)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        outRect.left = horizontalMarginInPx
        outRect.right = horizontalMarginInPx
    }
}

@BindingAdapter("bookGrid", "onBookClick")
fun RecyclerView.setBookGrid(books: List<Book>, onBookClick: BookClickListener) {
    val adapter = this.adapter as? BookGridAdapter ?: BookGridAdapter(onBookClick).also {
        it.setHasStableIds(true)
        this.adapter = it
    }

    adapter.submitList(books)
}

@BindingAdapter("searchResults", "onResultClick")
fun RecyclerView.setSearchResults(searchResults: List<SearchResult>, onResultClick: ResultClickListener) {
    val adapter = this.adapter as? SearchResultAdapter ?: SearchResultAdapter(onResultClick).also {
        it.setHasStableIds(true)
        this.adapter = it
    }

    adapter.submitList(searchResults)
}
