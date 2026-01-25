package com.ethan.sodium.app.ui.navigation

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

object AppDestination {
    /**
     * 启动页面
     */
    @Serializable
    @Immutable
    object SplashScreen
    @Serializable
    @Immutable
    object HOME_SCREEN
    /**
     * 主屏
     */
    @Serializable
    @Immutable
    object MAIN_SCREEN

    /**
     * 山河API 首页
     */
    @Serializable
    @Immutable
    object MAIN_SHAN_HE_SCREEN

    @Serializable
    @Immutable
    object SCREEN_MULTI_STATE

    /**
     * 详情页面
     */
    @Serializable
    data class  Detail(val age: Int =95 )
    @Serializable
    object ROUTE_THIRD
}