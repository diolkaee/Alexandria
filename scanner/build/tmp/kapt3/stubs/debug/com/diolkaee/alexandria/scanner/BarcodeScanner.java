package com.diolkaee.alexandria.scanner;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u001c\u0010\u0002\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00060\u0003j\u0002`\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0017J\b\u0010\u000e\u001a\u00020\u000fH\u0002R$\u0010\u0002\u001a\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00060\u0003j\u0002`\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/diolkaee/alexandria/scanner/BarcodeScanner;", "Landroidx/camera/core/ImageAnalysis$Analyzer;", "listener", "Lkotlin/Function1;", "", "", "", "Lcom/diolkaee/alexandria/scanner/BarcodeListener;", "(Lkotlin/jvm/functions/Function1;)V", "scanner", "Lcom/google/mlkit/vision/barcode/BarcodeScanner;", "analyze", "imageProxy", "Landroidx/camera/core/ImageProxy;", "buildScannerOptions", "Lcom/google/mlkit/vision/barcode/BarcodeScannerOptions;", "scanner_debug"})
public final class BarcodeScanner implements androidx.camera.core.ImageAnalysis.Analyzer {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.util.List<java.lang.Long>, kotlin.Unit> listener = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.mlkit.vision.barcode.BarcodeScanner scanner = null;
    
    public BarcodeScanner(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<java.lang.Long>, kotlin.Unit> listener) {
        super();
    }
    
    @java.lang.Override()
    @androidx.camera.core.ExperimentalGetImage()
    public void analyze(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy imageProxy) {
    }
    
    private final com.google.mlkit.vision.barcode.BarcodeScannerOptions buildScannerOptions() {
        return null;
    }
}