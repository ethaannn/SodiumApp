package com.ethan.sodium.app.data.interceptor

import com.ethan.sodium.app.data.DataConfig
import okhttp3.Interceptor

// 缓存控制拦截器
class CacheInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return response.newBuilder()
            .header("Cache-Control", "public, max-age=${DataConfig.CACHE_MAX_AGE}")
            .build()
    }
}