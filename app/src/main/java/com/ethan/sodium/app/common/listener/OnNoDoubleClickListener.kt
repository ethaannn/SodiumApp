package com.ethan.sodium.app.common.listener

import android.view.View
import android.widget.Toolbar
import java.util.*

/**
 * @author: ethan
 * @email: ethan.nj@qq.com
 * @modify_author:
 * @modify_date: 2021年4月28日
 * @description:
 * @date: 2021年4月28日
 */
abstract class OnNoDoubleClickListener : View.OnClickListener {
    private var lastClickTime: Long = 0
    override fun onClick(v: View) {
        val currentTime = Calendar.getInstance().timeInMillis
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime
            onNoDoubleClick(v)
        }
    }

    /**
     * 结果回调方法
     * @param v 被点击对象
     */
    abstract fun onNoDoubleClick(v: View)

    companion object {
        /**
         * 最小点击时间间隔
         */
        private const val MIN_CLICK_DELAY_TIME = 1000
    }
}

fun View.setNoDoubleClickListener(listener:(view: View)->Unit) {
    this.setOnClickListener(object : OnNoDoubleClickListener(){
        override fun onNoDoubleClick(v: View) {
            listener(v)
        }
    })
}
fun Toolbar.setNavigationNoDoubleClickListener(listener: (view: View) -> Unit){
    this.setNavigationOnClickListener(object : OnNoDoubleClickListener(){
        override fun onNoDoubleClick(v: View) {
            listener(v)
        }
    })
}