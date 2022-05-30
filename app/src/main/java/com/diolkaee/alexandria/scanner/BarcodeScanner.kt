package com.diolkaee.alexandria.scanner

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.barcode.common.Barcode.TYPE_ISBN
import com.google.mlkit.vision.common.InputImage

typealias BarcodeListener = (List<String>) -> Unit

private const val SCANNER_TAG = "BarcodeScanner"

class BarcodeScanner(private val listener: BarcodeListener) : ImageAnalysis.Analyzer {
    private val scanner: BarcodeScanner = BarcodeScanning.getClient(buildScannerOptions())

    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val inputImage =
                InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            scanner.process(inputImage)
                .addOnSuccessListener { barcodes ->
                    Log.d(SCANNER_TAG, "Detected barcodes: ${barcodes.map { it.rawValue }}")

                    val detectedISBNs = barcodes
                        .filter { it.valueType == TYPE_ISBN }
                        .mapNotNull { it.rawValue }
                        .filter { it.length == 10 || it.length == 13 }
                        .map {
                            when (it.length) {
                                10 -> "978$it"
                                13 -> it
                                else -> throw IllegalStateException("Illegal ISBN length")
                            }
                        }

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
