package com.diolkaee.alexandria.data

import androidx.room.Room
import com.diolkaee.alexandria.data.networking.ApiService
import com.diolkaee.alexandria.data.networking.BookListAdapter
import com.diolkaee.alexandria.data.persistence.BookDatabase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

val dataModule = module {
    // Http
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            )
            .build()
    }

    // Serialization
    single {
        Moshi.Builder()
            .add(BookListAdapter())
            .build()
    }
    single { MoshiConverterFactory.create(get()) as Converter.Factory }

    // Networking
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(get())
            .build()
    }
    single { get<Retrofit>().create<ApiService>() }

    // Persistence
    single {
        Room.databaseBuilder(
            get(),
            BookDatabase::class.java,
            BookDatabase.FILE_NAME
        )
            .build()
    }
    single { get<BookDatabase>().getBookDao() }
}
