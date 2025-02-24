package com.diolkaee.alexandria.scanner.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0014H\u0014J\b\u0010\u0019\u001a\u00020\u0014H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001d"}, d2 = {"Lcom/diolkaee/alexandria/scanner/ui/ScanActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/diolkaee/alexandria/scanner/databinding/ActivityScanBinding;", "cameraExecutor", "Ljava/util/concurrent/ExecutorService;", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "viewModel", "Lcom/diolkaee/alexandria/scanner/ui/ScanViewModel;", "getViewModel", "()Lcom/diolkaee/alexandria/scanner/ui/ScanViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "allPermissionsGranted", "", "finishActivity", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "setupEvents", "setupViews", "startCamera", "Companion", "scanner_debug"})
public final class ScanActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private com.diolkaee.alexandria.scanner.databinding.ActivityScanBinding binding;
    private java.util.concurrent.ExecutorService cameraExecutor;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> requestPermissionLauncher = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_TAG = "isbns";
    @org.jetbrains.annotations.NotNull()
    public static final com.diolkaee.alexandria.scanner.ui.ScanActivity.Companion Companion = null;
    
    public ScanActivity() {
        super();
    }
    
    private final com.diolkaee.alexandria.scanner.ui.ScanViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupViews() {
    }
    
    private final void setupEvents() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void startCamera() {
    }
    
    private final boolean allPermissionsGranted() {
        return false;
    }
    
    private final void finishActivity() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/diolkaee/alexandria/scanner/ui/ScanActivity$Companion;", "", "()V", "EXTRA_TAG", "", "scanner_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}