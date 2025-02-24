package com.diolkaee.alexandria.scanner.databinding;
import com.diolkaee.alexandria.scanner.R;
import com.diolkaee.alexandria.scanner.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityScanBindingImpl extends ActivityScanBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.viewFinder, 3);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    @NonNull
    private final com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton mboundView2;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityScanBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private ActivityScanBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (androidx.recyclerview.widget.RecyclerView) bindings[1]
            , (androidx.camera.view.PreviewView) bindings[3]
            );
        this.bookList.setTag(null);
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView2 = (com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton) bindings[2];
        this.mboundView2.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.searchResults == variableId) {
            setSearchResults((java.util.List<com.diolkaee.alexandria.business.book.Book>) variable);
        }
        else if (BR.onFinish == variableId) {
            setOnFinish((android.view.View.OnClickListener) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setSearchResults(@Nullable java.util.List<com.diolkaee.alexandria.business.book.Book> SearchResults) {
        this.mSearchResults = SearchResults;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.searchResults);
        super.requestRebind();
    }
    public void setOnFinish(@Nullable android.view.View.OnClickListener OnFinish) {
        this.mOnFinish = OnFinish;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.onFinish);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        java.util.List<com.diolkaee.alexandria.business.book.Book> searchResults = mSearchResults;
        android.view.View.OnClickListener onFinish = mOnFinish;

        if ((dirtyFlags & 0x5L) != 0) {
        }
        if ((dirtyFlags & 0x6L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            com.diolkaee.alexandria.scanner.ui.adapter.RecyclerViewExtKt.setBookList(this.bookList, searchResults, (com.diolkaee.alexandria.scanner.ui.adapter.BookClickListener)null);
        }
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.mboundView2.setOnClickListener(onFinish);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): searchResults
        flag 1 (0x2L): onFinish
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}