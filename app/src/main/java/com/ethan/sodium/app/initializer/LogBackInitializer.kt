package com.ethan.sodium.app.initializer

import android.content.Context
import androidx.startup.Initializer
import com.ethan.sodium.app.common.log.ImplLoggerManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class LogBackInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        runBlocking(Dispatchers.IO){
            ImplLoggerManager(context).init()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> {
       return emptyList()
    }
}