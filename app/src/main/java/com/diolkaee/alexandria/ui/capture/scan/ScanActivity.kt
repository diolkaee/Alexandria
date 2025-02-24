package com.diolkaee.alexandria.ui.capture.scan

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.diolkaee.alexandria.R
import com.diolkaee.alexandria.databinding.ActivityScanBinding
import com.diolkaee.alexandria.scanner.BarcodeScanner
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private const val LOG_TAG = "ScanActivity"
private const val CAMERA_PERMISSION = Manifest.permission.CAMERA

class ScanActivityContract : ActivityResultContract<Unit, List<Long>>() {
    override fun createIntent(context: Context, input: Unit): Intent = Intent(context, ScanActivity::class.java)

    override fun parseResult(resultCode: Int, intent: Intent?): List<Long> {
        val data = intent?.getLongArrayExtra(ScanActivity.EXTRA_TAG)?.toList()
        return if (resultCode == Activity.RESULT_OK && data != null) data else emptyList()
    }
}

class ScanActivity : AppCompatActivity() {
    private val viewModel: ScanViewModel by viewModel()

    private lateinit var binding: ActivityScanBinding
    private lateinit var cameraExecutor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupEvents()

        // Request camera permission
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissionLauncher.launch(CAMERA_PERMISSION)
        }

        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun setupViews() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchResults.collect {
                    binding.searchResults = it.toList()
                }
            }
        }
    }

    private fun setupEvents() = with(binding) {
        setOnFinish { finishActivity() }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            // Bind cameras lifecycle to lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            val imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .also {
                    it.setAnalyzer(
                        cameraExecutor,
                        BarcodeScanner { isbn ->
                            viewModel.searchBooks(isbn)
                        }
                    )
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer)
            } catch (e: Exception) {
                Log.e(LOG_TAG, "Binding use cases failed", e)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(baseContext, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            Toast.makeText(
                this,
                R.string.scan_permission_denied,
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    private fun finishActivity() {
        val data = Intent()
        val isbns = viewModel.searchResults.value.map { it.isbn }
        data.putExtra(EXTRA_TAG, isbns.toLongArray())
        setResult(RESULT_OK, data)
        finish()
    }

    companion object {
        const val EXTRA_TAG = "isbns"
    }
}
