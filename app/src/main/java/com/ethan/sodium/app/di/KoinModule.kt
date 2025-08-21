package com.ethan.sodium.app.di

import com.ethan.sodium.app.common.log.ImplLoggerManager
import com.ethan.sodium.app.data.DataConfig
import com.ethan.sodium.app.data.interceptor.CacheInterceptor
import com.ethan.sodium.app.data.repository.ShanHeRepository
import com.ethan.sodium.app.ui.viewmodel.AppViewModel
import com.ethan.sodium.app.ui.viewmodel.MultiStateViewModel
import com.ethan.sodium.app.ui.viewmodel.ViewModelShanHe
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val koinFactoryModule: Module = module {

    factory<ImplLoggerManager> {
        ImplLoggerManager(mContext = get())
    }

    viewModel { ViewModelShanHe() }
    viewModel { MultiStateViewModel() }
    factory<ShanHeRepository> { ShanHeRepository() }
}





val koinCommonModule: Module = module {

    single {
        Moshi.Builder()
            .build()
    }
    single {
        AppViewModel(application = get())
    }

    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val cacheDir =androidContext().cacheDir
        val cacheSize = 10 * 1024 * 1024L //10MB 缓存大小
        val cache =okhttp3.Cache(cacheDir, cacheSize)

        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .cache(cache = cache)
            .addNetworkInterceptor(interceptor = CacheInterceptor())
            .connectTimeout(timeout = DataConfig.connectTimeOut, TimeUnit.SECONDS)
            .readTimeout(timeout = DataConfig.readTimeOut, TimeUnit.SECONDS)
            .writeTimeout(timeout = DataConfig.writeTimeOut, TimeUnit.SECONDS)
            .retryOnConnectionFailure(retryOnConnectionFailure = true)// 启用失败重连
            .build()
    }


    single {
        Retrofit.Builder()
            .baseUrl(DataConfig.BASE_URL) // 示例API地址
            .client(get<OkHttpClient>())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }
    // 添加泛型工厂函数
    factory { (apiClass: Class<*>) ->
        get<Retrofit>().create(apiClass)
    }
}