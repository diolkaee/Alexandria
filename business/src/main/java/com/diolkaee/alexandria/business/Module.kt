package com.diolkaee.alexandria.business

import com.diolkaee.alexandria.business.book.BookRepository
import com.diolkaee.alexandria.data.dataModule
import org.koin.dsl.module

val businessModule = dataModule + module {
    single { BookRepository(get(), get(), mocked = true) }
}
