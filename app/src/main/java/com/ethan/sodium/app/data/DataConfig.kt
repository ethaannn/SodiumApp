package com.ethan.sodium.app.data

import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get

object DataConfig {
    val BASE_URL = "http://shanhe.kim/"


    val connectTimeOut: Long = 30L // 单位秒
    val readTimeOut: Long = 30L // 单位秒
    val writeTimeOut: Long = 30 // 单位秒

    val CACHE_MAX_AGE=5*60 //单位5分钟
}

inline fun <reified T : Any> getApiService(): T {
    return get(T::class.java, parameters = { parametersOf(T::class.java) })
}
