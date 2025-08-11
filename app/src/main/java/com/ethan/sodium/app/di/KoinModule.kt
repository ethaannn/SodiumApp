package com.ethan.sodium.app.di

import com.ethan.sodium.app.common.log.ImplLoggerManager
import org.koin.core.module.Module
import org.koin.dsl.module

 val koinFactoryModule : Module = module {

     factory<ImplLoggerManager>{
         ImplLoggerManager(mContext =get())
     }

}