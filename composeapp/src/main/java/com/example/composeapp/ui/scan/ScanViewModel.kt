package com.example.composeapp.ui.scan

import android.content.Context
import androidx.camera.core.CameraSelector.DEFAULT_BACK_CAMERA
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.lifecycle.awaitInstance
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diolkaee.alexandria.business.book.Book
import com.diolkaee.alexandria.business.book.BookRepository
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

data class ScanState(val searchResults: List<Book> = emptyList())

class ScanViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private val searchResults = MutableStateFlow<List<Book>>(emptyList())
    private val currentSearch = MutableStateFlow<List<Long>>(emptyList())

    private val isbnScanner = ImageAnalysis.Builder().build()
        .apply {
            setAnalyzer(
                Executors.newSingleThreadExecutor(),
                IsbnScanner { isbnResults ->
                    isbnResults.forEach(::searchIsbn)
                },
            )
        }

    val uiState = searchResults
        .map(::ScanState)
        .stateIn(viewModelScope, SharingStarted.Eagerly, ScanState())

    // Camera setup
    private val _surfaceRequest = MutableStateFlow<SurfaceRequest?>(null)
    val surfaceRequest: StateFlow<SurfaceRequest?> = _surfaceRequest

    private val cameraPreviewUseCase = Preview.Builder().build().apply {
        setSurfaceProvider { newSurfaceRequest ->
            _surfaceRequest.update { newSurfaceRequest }
        }
    }

    suspend fun bindToCamera(appContext: Context, lifecycleOwner: LifecycleOwner) {
        val processCameraProvider = ProcessCameraProvider.awaitInstance(appContext)
        processCameraProvider.bindToLifecycle(
            lifecycleOwner,
            DEFAULT_BACK_CAMERA,
            cameraPreviewUseCase,
            isbnScanner,
        )

        // Cancellation signals we're done with the camera
        try {
            awaitCancellation()
        } finally {
            processCameraProvider.unbindAll()
        }
    }

    fun searchIsbn(isbn: Long) {
        if (!currentSearch.value.contains(isbn) && !searchResults.value.containsIsbn(isbn)) {
            currentSearch.update { it.plus(isbn) }
            viewModelScope.launch {
                val results = bookRepository.fetch(isbn)
                if (results.isNotEmpty()) {
                    searchResults.update { it.plus(results).distinct() }
                }
                currentSearch.update { it.minus(isbn) }
            }
        }
    }

    fun archive(book: Book) {
        viewModelScope.launch {
            bookRepository.insert(book)
            searchResults.update { it.minus(book) }
        }
    }
}

private fun List<Book>.containsIsbn(isbn: Long) = any { it.isbn == isbn }