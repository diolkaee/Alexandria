package com.diolkaee.alexandria.common.image

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String?) {
    load(url)
}
