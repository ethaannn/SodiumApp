package com.ethan.sodium.app.ui.base

import android.content.Intent
import android.os.Bundle
import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class ActivityBusinessBase :ActivityCoreBase(){

    protected val mLogger: Logger by lazy { LoggerFactory.getLogger(this.javaClass) }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        mLogger.info( "SLF4J:ActivityBusinessBase:onNewIntent")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLogger.info( "SLF4J:ActivityBusinessBase:onCreate")
    }

    override fun onStart() {
        super.onStart()
        mLogger.info( "SLF4J:ActivityBusinessBase:onStart")
    }

    override fun onResume() {
        super.onResume()
        mLogger.info( "SLF4J:ActivityBusinessBase:onResume")
    }

    override fun onPause() {
        super.onPause()
        mLogger.info( "SLF4J:ActivityBusinessBase:onPause")
    }
    override fun onStop() {
        super.onStop()
        mLogger.info( "SLF4J:ActivityBusinessBase:onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mLogger.info( "SLF4J:ActivityBusinessBase:onDestroy")
    }
}
