package com.example.composeapp.ui.scan

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.common.Barcode.TYPE_ISBN
import com.google.mlkit.vision.common.InputImage

typealias BarcodeListener = (List<Long>) -> Unit

private const val LOG_TAG = "BarcodeScanner"

class IsbnScanner(private val listener: BarcodeListener) : ImageAnalysis.Analyzer {
    private val scanner: BarcodeScanner = BarcodeScanning.getClient(buildScannerOptions())

    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val inputImage =
                InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            scanner.process(inputImage)
                .addOnSuccessListener { barcodes ->
                    Log.d(LOG_TAG, "Detected barcodes: ${barcodes.map { it.rawValue }}")

                    val detectedISBNs = barcodes
                        .filter { it.valueType == TYPE_ISBN }
                        .mapNotNull { it.rawValue }
                        .filter { it.length == 13 }
                        .map { it.toLong() }

                    if (detectedISBNs.isNotEmpty()) {
                        listener(detectedISBNs)
                    }
                }
                .addOnCompleteListener { imageProxy.close() }
        }
    }

    private fun buildScannerOptions() = BarcodeScannerOptions
        .Builder()
        .setBarcodeFormats(Barcode.FORMAT_EAN_13)
        .build()
}
