package com.djevannn.capstoneproject.di

import androidx.room.Room
import com.djevannn.capstoneproject.data.BookDataSource
import com.djevannn.capstoneproject.data.BookRepository
import com.djevannn.capstoneproject.data.source.local.LocalDataSource
import com.djevannn.capstoneproject.data.source.local.room.BookDatabase
import com.djevannn.capstoneproject.data.source.remote.ApiService
import com.djevannn.capstoneproject.data.source.remote.RemoteDataSource
import com.djevannn.capstoneproject.ui.home.HomeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<BookDatabase>().bookDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            BookDatabase::class.java,
            "Book.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        // change here later
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tourism-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<BookDataSource> { BookRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            BookRepository(
                RemoteDataSource(get()),
                LocalDataSource(get())
            )
        )
    }
}