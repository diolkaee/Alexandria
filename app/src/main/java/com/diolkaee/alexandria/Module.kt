package com.diolkaee.alexandria

import com.diolkaee.alexandria.business.businessModule
import com.diolkaee.alexandria.ui.capture.CaptureViewModel
import com.diolkaee.alexandria.ui.capture.scan.ScanViewModel
import com.diolkaee.alexandria.ui.shelf.ShelfViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = businessModule + module {
    viewModel { ShelfViewModel(get()) }
    viewModel { CaptureViewModel(get()) }
    viewModel { ScanViewModel(get()) }
}
