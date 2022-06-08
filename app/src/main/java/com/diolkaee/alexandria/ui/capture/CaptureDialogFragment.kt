package com.diolkaee.alexandria.ui.capture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.diolkaee.alexandria.R
import com.diolkaee.alexandria.databinding.DialogCaptureBinding
import com.diolkaee.alexandria.ui.capture.scan.ScanActivityContract
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CaptureDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: DialogCaptureBinding
    private val viewModel: CaptureViewModel by viewModel()

    private val scanBookIntent = registerForActivityResult(ScanActivityContract()) { result: List<Long> ->
        result.forEach { viewModel.fetchBooks(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogCaptureBinding.inflate(inflater, container, false)
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
        searchInput.onSubmit(::handleSearchInput)
        setOnScan { scanBookIntent.launch(Unit) }
        setOnAdd { viewModel.toggleMarked(it.book.isbn) }
        setOnFinish { finishDialog() }
    }

    private fun finishDialog() {
        viewModel.archiveMarkedBooks().invokeOnCompletion { dismiss() }
    }

    private fun handleSearchInput(text: CharSequence?) {
        val isSearchValid = text?.length == 10 || text?.length == 13
        val error: String? = when {
            text.isNullOrBlank() -> null
            // This branch is true if text is null or blank, but then we return null before so no error is shown
            !isSearchValid -> requireContext().resources.getString(R.string.capture_input_error)
            else -> null
        }

        if (isSearchValid) viewModel.fetchBooks(text.toString().toLong())
        binding.inputError = error
    }
}

private fun TextInputEditText.onSubmit(func: (text: CharSequence?) -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            func(text)
        }
        // Allow default action
        false
    }
}
