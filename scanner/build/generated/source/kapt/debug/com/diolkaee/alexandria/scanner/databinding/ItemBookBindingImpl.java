package com.diolkaee.alexandria.scanner.databinding;
import com.diolkaee.alexandria.scanner.R;
import com.diolkaee.alexandria.scanner.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ItemBookBindingImpl extends ItemBookBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final com.google.android.material.card.MaterialCardView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ItemBookBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 2, sIncludes, sViewsWithIds));
    }
    private ItemBookBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.CheckedTextView) bindings[1]
            );
        this.mboundView0 = (com.google.android.material.card.MaterialCardView) bindings[0];
        this.mboundView0.setTag(null);
        this.title.setTag(null);
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
        if (BR.book == variableId) {
            setBook((com.diolkaee.alexandria.business.book.Book) variable);
        }
        else if (BR.onClick == variableId) {
            setOnClick((android.view.View.OnClickListener) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setBook(@Nullable com.diolkaee.alexandria.business.book.Book Book) {
        this.mBook = Book;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.book);
        super.requestRebind();
    }
    public void setOnClick(@Nullable android.view.View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.onClick);
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
        boolean bookMarked = false;
        java.lang.String bookTitle = null;
        com.diolkaee.alexandria.business.book.Book book = mBook;
        android.view.View.OnClickListener onClick = mOnClick;

        if ((dirtyFlags & 0x5L) != 0) {



                if (book != null) {
                    // read book.marked
                    bookMarked = book.getMarked();
                    // read book.title
                    bookTitle = book.getTitle();
                }
        }
        if ((dirtyFlags & 0x6L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x6L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(onClick);
        }
        if ((dirtyFlags & 0x5L) != 0) {
            // api target 1

            this.title.setChecked(bookMarked);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.title, bookTitle);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): book
        flag 1 (0x2L): onClick
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}