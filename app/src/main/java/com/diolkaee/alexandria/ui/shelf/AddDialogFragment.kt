package com.diolkaee.alexandria.ui.shelf

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.diolkaee.alexandria.databinding.DialogAddBinding
import com.diolkaee.alexandria.ui.scan.ScanActivityContract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

fun interface ResultClickListener {
    operator fun invoke(result: SearchResult)
}

class AddDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: DialogAddBinding
    private val viewModel: AddDialogViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogAddBinding.inflate(inflater, container, false)
        setupViews()
        setupEvents()
        return binding.root
    }

    private fun setupViews() = with(binding) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchResults.collect {
                    searchResults = it
                }
            }
        }
    }

    private fun setupEvents() = with(binding) {
        search.onSubmit {
            val searchString = search.text.toString()
            if (searchString.isNotBlank()) viewModel.fetchBooks(searchString.toLong())
        }

        setOnScan { scanBookActivity.launch(Unit) }
        setOnAdd { viewModel.toggleMark(it) }
        setOnFinish { finish() }
    }

    private fun teardownEvents() = with(binding) {
        // TODO Remove search listener
    }

    private val scanBookActivity = registerForActivityResult(ScanActivityContract()) { result: List<Long> ->
        result.forEach { viewModel.fetchBooks(it) }
    }

    private fun finish() {
        viewModel.archiveMarkedBooks().invokeOnCompletion { dismiss() }
    }
}

private fun EditText.onSubmit(func: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            func()
        }
        false
    }
}
