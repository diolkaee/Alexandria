package com.example.composeapp

import com.diolkaee.alexandria.business.businessModule
import com.example.composeapp.ui.shelf.ShelfViewModel
import com.example.composeapp.ui.scan.ScanViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = businessModule + module {
    viewModelOf(::ShelfViewModel)
    viewModelOf(::ScanViewModel)
}
