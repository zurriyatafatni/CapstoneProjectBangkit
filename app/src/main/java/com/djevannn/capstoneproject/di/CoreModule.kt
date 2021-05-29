package com.djevannn.capstoneproject.di

import androidx.room.Room
import com.djevannn.capstoneproject.data.BookDataSource
import com.djevannn.capstoneproject.data.BookRepository
import com.djevannn.capstoneproject.data.source.local.LocalDataSource
import com.djevannn.capstoneproject.data.source.local.room.BookDatabase
import com.djevannn.capstoneproject.data.source.remote.RemoteDataSource
import com.djevannn.capstoneproject.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

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

//val networkModule = module {
//    single {
//        OkHttpClient.Builder()
//            .addInterceptor(
//                HttpLoggingInterceptor().setLevel(
//                    HttpLoggingInterceptor.Level.BODY
//                )
//            )
//            .connectTimeout(120, TimeUnit.SECONDS)
//            .readTimeout(120, TimeUnit.SECONDS)
//            .build()
//    }
//    single {
//        // change here later
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://tourism-api.dicoding.dev/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(get())
//            .build()
//        retrofit.create(ApiService::class.java)
//    }
//}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single<BookDataSource> { BookRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel {
        HomeViewModel(
            BookRepository(
                RemoteDataSource(),
                LocalDataSource(get())
            )
        )
    }
}