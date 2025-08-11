package com.ethan.sodium.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.ethan.sodium.app.di.koinFactoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import kotlin.io.println

class KoinInitializer: Initializer<Unit>{
    override fun create(context: Context) {

        startKoin {
            androidLogger()
            androidContext(androidContext=context)
            modules(koinFactoryModule)
            println("SLF4J:KoinInitializer:create:startKoin success")
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(LogBackInitializer::class.java)
    }

}