package com.ethan.sodium.app

import com.ethan.sodium.app.ui.base.ApplicationBaseCore


/**
 * @author ethan
 */
class App : ApplicationBaseCore(){
    companion object {
        fun getInstance(): App {
            return instance as App
        }
    }
    override fun initComponent() {
        mLogger.info("LOG:App:initComponent")
    }

}