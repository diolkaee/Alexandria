package com.diolkaee.alexandria.scanner.ui.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00028\u0000H\u0017\u00a2\u0006\u0002\u0010\tJ\u001d\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\t\u00a8\u0006\u000b"}, d2 = {"Lcom/diolkaee/alexandria/scanner/ui/adapter/CommonDiffCallback;", "T", "", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "areItemsTheSame", "scanner_debug"})
public final class CommonDiffCallback<T extends java.lang.Object> extends androidx.recyclerview.widget.DiffUtil.ItemCallback<T> {
    
    public CommonDiffCallback() {
        super();
    }
    
    @java.lang.Override()
    public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
    T oldItem, @org.jetbrains.annotations.NotNull()
    T newItem) {
        return false;
    }
    
    @java.lang.Override()
    @android.annotation.SuppressLint(value = {"DiffUtilEquals"})
    public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
    T oldItem, @org.jetbrains.annotations.NotNull()
    T newItem) {
        return false;
    }
}