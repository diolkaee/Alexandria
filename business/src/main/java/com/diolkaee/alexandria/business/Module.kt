package com.diolkaee.alexandria.business

import com.diolkaee.alexandria.business.book.BookRepository
import com.diolkaee.alexandria.data.dataModule
import kotlinx.coroutines.MainScope
import org.koin.dsl.module

val businessModule = dataModule + module {
    /** As we keep a single instance for the whole lifetime, we can pass an application scoped [MainScope] */
    single { BookRepository(get(), get(), MainScope()) }
}
