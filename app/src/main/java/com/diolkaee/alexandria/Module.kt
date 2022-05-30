package com.diolkaee.alexandria

import com.diolkaee.alexandria.business.businessModule
import com.diolkaee.alexandria.ui.scan.ScanViewModel
import com.diolkaee.alexandria.ui.shelf.ShelfViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = businessModule + module {
    viewModel { ShelfViewModel(get()) }
    viewModel { ScanViewModel(get()) }
}
