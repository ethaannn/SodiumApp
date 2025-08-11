package com.ethan.sodium.app.ui.base

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import com.ethan.sodium.app.common.log.ImplLoggerManager
import com.ethan.sodium.app.R
import com.ethan.sodium.app.common.toast.FactoryToast
import com.ethan.sodium.app.common.toast.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.getValue

abstract class ApplicationBaseCore : Application(), Thread.UncaughtExceptionHandler {

    protected val mLogger: Logger by lazy { LoggerFactory.getLogger(this.javaClass) }
    private val mLoggerManager by lazy { ImplLoggerManager(mContext = this.baseContext) }


    companion object{
        @Volatile
        var instance: ApplicationBaseCore? = null
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Handler(Looper.getMainLooper()).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (e: Throwable) {
                    FactoryToast.makeText(baseContext, getString(R.string.error_message_application_crash), Toast.LENGTH_LONG).show()
                    mLogger.error("LOG:ApplicationPen:attachBaseContext", e)
                }
            }
        }
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        runBlocking { mLoggerManager.init() }
        CoroutineScope(Dispatchers.Main).launch { initComponent() }
    }
     abstract fun initComponent()

    override fun uncaughtException(t: Thread, e: Throwable) {
        mLogger.error("LOG:App:uncaughtException t={}", t, e)
    }

}