package com.ethan.sodium.ui.components.badge

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Badge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @param isBadgeVisible 徽章是否可见，默认可见,可通过外部函数控制可见
 * @param alignment 徽章位置 默认右上角，可以通过
 * Alignment.CenterStart
 * Alignment.CenterEnd,
 * Alignment.Center,
 * Alignment.TopCenter,
 * Alignment.BottomCenter,
 * Alignment.TopEnd,
 * Alignment.BottomEnd 指定
 * @param badgeSize 徽章吃春 是一个DpSize  width 是最小宽度，height 是固定高度
 * @param badgeColor 徽章背景色
 * @param defaultBadgeText 默认徽章文本
 * @param badgeContent 自定义徽章内容,默认是文本。可传入一个composable 函数，当自定义时，传入的comoposable函数是一个非文本， defaultBadgeText ，fontSize,badgeFontColor 则无效。
 * @param badgeFontColor 徽章字体颜色
 * @param fontSize 徽章字体大小
 * @param badgeContent 徽章内容默认是文本，可通过外部函数自定义
 * @param badgeAnchor 徽章锚点
 */

@Composable
fun SodiumBadgeBox(
    isBadgeVisible: () -> Boolean = { true },
    alignment: Alignment = Alignment.TopEnd,
    badgeSize: DpSize = DpSize(20.dp, 20.dp),
    badgeColor: Color = Color.Red,
    badgeFontColor: Color = Color.White,
    fontSize: TextUnit = 12.sp,
    defaultBadgeText: String = "",
    badgeContent: @Composable () -> Unit = {
        Text(text = defaultBadgeText, fontSize = fontSize)
    },
    badgeAnchor: @Composable () -> Unit
) {

    Box(modifier = Modifier.wrapContentSize()) {

        val offsetX = when (alignment) {
            Alignment.TopEnd, Alignment.BottomEnd                         -> badgeSize.width / 2
            Alignment.TopCenter, Alignment.BottomCenter, Alignment.Center -> 0.dp
            Alignment.CenterStart                                         -> -(badgeSize.width + 8.dp)
            Alignment.CenterEnd                                           -> badgeSize.width + 8.dp

            else                                                          -> -badgeSize.width / 2
        }
        val offsetY = when (alignment) {
            Alignment.BottomStart, Alignment.BottomCenter, Alignment.BottomEnd -> badgeSize.height / 2
            Alignment.CenterEnd, Alignment.CenterStart, Alignment.Center       -> 0.dp

            else                                                               -> -badgeSize.height / 2
        }

        badgeAnchor.invoke()

        if (isBadgeVisible.invoke()) {
            Badge(modifier = Modifier
                .align(alignment)
                .widthIn(badgeSize.width)
                .height(badgeSize.height)
                .offset(x = offsetX, y = offsetY),
                  contentColor = badgeFontColor,
                  containerColor = badgeColor,
                  content = {
                      badgeContent.invoke()
                  })
        }
    }
}