package com.ethan.sodium.ui.components.multistate


enum class MultiState {
    /**
     * 显示内容
     */
    CONTENT,
    /**
     * 显示空数据
     */
    EMPTY,

    /**
     * 数据请求错误失败等
     */
    ERROR,

    /**
     * 页面数据加载中
     */
    LOADING
}