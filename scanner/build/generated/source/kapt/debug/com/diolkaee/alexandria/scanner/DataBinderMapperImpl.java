package com.diolkaee.alexandria.scanner;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.diolkaee.alexandria.scanner.databinding.ActivityScanBindingImpl;
import com.diolkaee.alexandria.scanner.databinding.ItemAuthorBindingImpl;
import com.diolkaee.alexandria.scanner.databinding.ItemBookBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYSCAN = 1;

  private static final int LAYOUT_ITEMAUTHOR = 2;

  private static final int LAYOUT_ITEMBOOK = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.diolkaee.alexandria.scanner.R.layout.activity_scan, LAYOUT_ACTIVITYSCAN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.diolkaee.alexandria.scanner.R.layout.item_author, LAYOUT_ITEMAUTHOR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.diolkaee.alexandria.scanner.R.layout.item_book, LAYOUT_ITEMBOOK);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYSCAN: {
          if ("layout/activity_scan_0".equals(tag)) {
            return new ActivityScanBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_scan is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMAUTHOR: {
          if ("layout/item_author_0".equals(tag)) {
            return new ItemAuthorBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_author is invalid. Received: " + tag);
        }
        case  LAYOUT_ITEMBOOK: {
          if ("layout/item_book_0".equals(tag)) {
            return new ItemBookBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for item_book is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(6);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "book");
      sKeys.put(2, "name");
      sKeys.put(3, "onClick");
      sKeys.put(4, "onFinish");
      sKeys.put(5, "searchResults");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/activity_scan_0", com.diolkaee.alexandria.scanner.R.layout.activity_scan);
      sKeys.put("layout/item_author_0", com.diolkaee.alexandria.scanner.R.layout.item_author);
      sKeys.put("layout/item_book_0", com.diolkaee.alexandria.scanner.R.layout.item_book);
    }
  }
}
