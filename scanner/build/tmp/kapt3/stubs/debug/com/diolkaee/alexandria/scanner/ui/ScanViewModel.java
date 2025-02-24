package com.diolkaee.alexandria.scanner.ui;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0014"}, d2 = {"Lcom/diolkaee/alexandria/scanner/ui/ScanViewModel;", "Landroidx/lifecycle/ViewModel;", "bookRepository", "Lcom/diolkaee/alexandria/business/book/BookRepository;", "(Lcom/diolkaee/alexandria/business/book/BookRepository;)V", "_searchResults", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/diolkaee/alexandria/business/book/Book;", "currentSearch", "", "", "searchResults", "Lkotlinx/coroutines/flow/StateFlow;", "getSearchResults", "()Lkotlinx/coroutines/flow/StateFlow;", "searchBooks", "", "isbns", "", "scanner_debug"})
public final class ScanViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.diolkaee.alexandria.business.book.BookRepository bookRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<com.diolkaee.alexandria.business.book.Book>> _searchResults = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Set<com.diolkaee.alexandria.business.book.Book>> searchResults = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Long> currentSearch = null;
    
    public ScanViewModel(@org.jetbrains.annotations.NotNull()
    com.diolkaee.alexandria.business.book.BookRepository bookRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Set<com.diolkaee.alexandria.business.book.Book>> getSearchResults() {
        return null;
    }
    
    /**
     * Search for the incoming ISBNs. We need to keep track of currently searched ISBNs,
     * since the scanner updates faster than we get API results.
     */
    public final void searchBooks(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> isbns) {
    }
}