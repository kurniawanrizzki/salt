package com.noscale.salt

import android.app.Application
import com.noscale.salt.di.networkModules
import com.noscale.salt.di.repositoryModules
import com.noscale.salt.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(
                networkModules,
                repositoryModules,
                viewModelModules
            ))
        }
    }
}