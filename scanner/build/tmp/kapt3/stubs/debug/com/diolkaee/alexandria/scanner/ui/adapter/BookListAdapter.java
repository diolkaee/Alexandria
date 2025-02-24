package com.diolkaee.alexandria.scanner.ui.adapter;

/**
 * RecyclerView adapter to display BookItems grouped by their author.
 * To add items to the adapter, use [updateList] instead of [submitList]!
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0015\u0016B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0016\u0010\u0011\u001a\u00020\u000b2\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/diolkaee/alexandria/scanner/ui/adapter/BookListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/diolkaee/alexandria/scanner/ui/adapter/BookListItem;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "clickListener", "Lcom/diolkaee/alexandria/scanner/ui/adapter/BookClickListener;", "(Lcom/diolkaee/alexandria/scanner/ui/adapter/BookClickListener;)V", "getItemViewType", "", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateList", "list", "", "Lcom/diolkaee/alexandria/business/book/Book;", "AuthorViewHolder", "BookViewHolder", "scanner_debug"})
public final class BookListAdapter extends androidx.recyclerview.widget.ListAdapter<com.diolkaee.alexandria.scanner.ui.adapter.BookListItem, androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final com.diolkaee.alexandria.scanner.ui.adapter.BookClickListener clickListener = null;
    
    public BookListAdapter(@org.jetbrains.annotations.NotNull()
    com.diolkaee.alexandria.scanner.ui.adapter.BookClickListener clickListener) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    public final void updateList(@org.jetbrains.annotations.Nullable()
    java.util.List<com.diolkaee.alexandria.business.book.Book> list) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/diolkaee/alexandria/scanner/ui/adapter/BookListAdapter$AuthorViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/diolkaee/alexandria/scanner/databinding/ItemAuthorBinding;", "(Lcom/diolkaee/alexandria/scanner/databinding/ItemAuthorBinding;)V", "bind", "", "name", "", "Companion", "scanner_debug"})
    public static final class AuthorViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.diolkaee.alexandria.scanner.databinding.ItemAuthorBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        public static final com.diolkaee.alexandria.scanner.ui.adapter.BookListAdapter.AuthorViewHolder.Companion Companion = null;
        
        public AuthorViewHolder(@org.jetbrains.annotations.NotNull()
        com.diolkaee.alexandria.scanner.databinding.ItemAuthorBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        java.lang.String name) {
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/diolkaee/alexandria/scanner/ui/adapter/BookListAdapter$AuthorViewHolder$Companion;", "", "()V", "from", "Lcom/diolkaee/alexandria/scanner/ui/adapter/BookListAdapter$AuthorViewHolder;", "parent", "Landroid/view/ViewGroup;", "scanner_debug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.diolkaee.alexandria.scanner.ui.adapter.BookListAdapter.AuthorViewHolder from(@org.jetbrains.annotations.NotNull()
            android.view.ViewGroup parent) {
                return null;
            }
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/diolkaee/alexandria/scanner/ui/adapter/BookListAdapter$BookViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/diolkaee/alexandria/scanner/databinding/ItemBookBinding;", "(Lcom/diolkaee/alexandria/scanner/databinding/ItemBookBinding;)V", "bind", "", "item", "Lcom/diolkaee/alexandria/business/book/Book;", "clickListener", "Lcom/diolkaee/alexandria/scanner/ui/adapter/BookClickListener;", "Companion", "scanner_debug"})
    public static final class BookViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.diolkaee.alexandria.scanner.databinding.ItemBookBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        public static final com.diolkaee.alexandria.scanner.ui.adapter.BookListAdapter.BookViewHolder.Companion Companion = null;
        
        public BookViewHolder(@org.jetbrains.annotations.NotNull()
        com.diolkaee.alexandria.scanner.databinding.ItemBookBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.diolkaee.alexandria.business.book.Book item, @org.jetbrains.annotations.NotNull()
        com.diolkaee.alexandria.scanner.ui.adapter.BookClickListener clickListener) {
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/diolkaee/alexandria/scanner/ui/adapter/BookListAdapter$BookViewHolder$Companion;", "", "()V", "from", "Lcom/diolkaee/alexandria/scanner/ui/adapter/BookListAdapter$BookViewHolder;", "parent", "Landroid/view/ViewGroup;", "scanner_debug"})
        public static final class Companion {
            
            private Companion() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.diolkaee.alexandria.scanner.ui.adapter.BookListAdapter.BookViewHolder from(@org.jetbrains.annotations.NotNull()
            android.view.ViewGroup parent) {
                return null;
            }
        }
    }
}