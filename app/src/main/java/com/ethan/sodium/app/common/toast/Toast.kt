package com.ethan.sodium.app.common.toast

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import androidx.annotation.IntDef
import com.ethan.sodium.app.databinding.LayoutCustomToastBinding

/**
 * 自定义Toast实现
 * 注意：
 *   安卓版本R(11)之后将禁用自定义Toast弹出。
 *   在使用时请尽量使用{@link FactoryToast}
 *
 * @author ethan
 * @date   2021-04-10
 * @see FactoryToast
 */
@Suppress(names = ["DEPRECATION"])
class Toast(context: Context,gravity: Int) : android.widget.Toast(context) {

    @IntDef(value = [LENGTH_SHORT, LENGTH_LONG])
    @Retention(value = AnnotationRetention.SOURCE)
    annotation class Duration

    companion object {
        const val LENGTH_SHORT = 0
        const val LENGTH_LONG = 1

        fun makeText(context: Context, text: CharSequence, @Duration duration: Int, gravity: Int=Gravity.CENTER): Toast {
            val toast = Toast(context,gravity)
            toast.viewBinding.textToast.text = text
            toast.duration = duration
            return toast
        }
    }

    private var viewBinding: LayoutCustomToastBinding = LayoutCustomToastBinding.inflate(LayoutInflater.from(context), null, false)

    init {
        view = viewBinding.root
        setGravity(gravity, 0, 50)
    }


}