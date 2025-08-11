package com.ethan.sodium.app.common.log

import android.net.Uri


internal interface ILoggerManager {
    suspend fun init()

    fun zip(): Uri
}