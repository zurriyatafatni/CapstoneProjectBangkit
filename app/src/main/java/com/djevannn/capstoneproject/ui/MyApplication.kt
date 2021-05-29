package com.djevannn.capstoneproject.ui

import android.app.Application
import com.djevannn.capstoneproject.di.databaseModule
import com.djevannn.capstoneproject.di.repositoryModule
import com.djevannn.capstoneproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}