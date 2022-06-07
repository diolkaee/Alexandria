package com.diolkaee.alexandria.ui.shelf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diolkaee.alexandria.databinding.FragmentShelfBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val LOG_TAG = "ShelfFragment"

class ShelfFragment : Fragment() {
    private lateinit var binding: FragmentShelfBinding
    private val viewModel: ShelfViewModel by viewModel()

    private val navController: NavController
        get() = findNavController()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShelfBinding.inflate(inflater, container, false)
        setupViews()
        setupEvents()

        return binding.root
    }

    override fun onDestroyView() {
        teardownEvents()
        super.onDestroyView()
    }

    private fun setupViews() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.books.collect {
                    binding.books = it
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.scrollPosition.collect {
                    binding.scrollPosition = it
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.layout.collect {
                    binding.layout = it
                }
            }
        }
    }

    private fun setupEvents() = with(binding) {
        // Observe current topmost item so we can show the according cover in ViewPager
        bookList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            private var previousPosition: Int = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val newPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                if (newPosition != previousPosition) {
                    viewModel.setHighlightedBookIndex(newPosition)
                    previousPosition = newPosition
                }
            }
        })

        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = true

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.setQuery(newText)
                return true
            }
        })

        setOnSortBooks { viewModel.toggleSorting() }
        setOnAddBook { openAddDialog() }
        setOnChangeLayout { viewModel.advanceLayout() }
        setOnViewDetails { openDetails(it.isbn) }
    }

    private fun teardownEvents() = with(binding) {
        search.setOnQueryTextListener(null)
        bookList.clearOnScrollListeners()
    }

    private fun openAddDialog() = navController.navigate(ShelfFragmentDirections.shelfToCapture())

    private fun openDetails(isbn: Long) = navController.navigate(ShelfFragmentDirections.shelfToDetails(isbn))
}
