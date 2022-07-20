package com.example.apidatapractise.container

import android.app.Application
import android.util.Log.DEBUG
import android.util.Log.ERROR
import com.example.apidatapractise.BuildConfig.DEBUG
import com.example.apidatapractise.Dependency.retrofitBuilderModule
import org.koin.android.BuildConfig.DEBUG
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.logging.Level


class BaseApplocation : Application() {
    override fun onCreate() {
        super.onCreate()
        // now we have to start koin
        startKoin {
            ///call module here
            androidLogger(org.koin.core.logger.Level.ERROR)
            androidContext(this@BaseApplocation)
            modules(retrofitBuilderModule,)

        }
    }
}