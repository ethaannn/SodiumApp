package com.ethan.sodium.extension

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.semantics.Role


/**
 * 带防抖功能的clickable修饰符，300毫秒内只响应第一次点击
 *
 * @param enabled 是否启用点击
 * @param onClick 点击事件回调
 * @param role 语义角色
 * @param interactionSource 交互源
 * @param indication 视觉反馈指示
 */
fun Modifier.debounceClickable(
        debounceTimeout: Long = 300L,
        enabled: Boolean = true,
        role: Role? = null,
        interactionSource: MutableInteractionSource? = null,
        indication: androidx.compose.foundation.Indication? = null,
        onClick: () -> Unit,
): Modifier = composed {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    val currentInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    val currentIndication = indication

    clickable(
        enabled = enabled,
        onClick = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > debounceTimeout) {
                lastClickTime = currentTime
                onClick()
            }
        },
        role = role,
        interactionSource = currentInteractionSource,
        indication = currentIndication
    )
}

