package com.phatnhse.hn.threads

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.phatnhse.hn.threads.di.commonModules
import com.phatnhse.hn.threads.di.initKoin
import com.phatnhse.hn.threads.di.platformModule
import org.koin.core.context.startKoin

class Application : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this

        initKoin()
    }
}