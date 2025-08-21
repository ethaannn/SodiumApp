package com.ethan.sodium.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
/**
 * 自定义滚动视图组件，支持垂直和水平滚动方向
 *
 * @param modifier 用于修饰该组件的Modifier，默认为Modifier
 * @param orientation 滚动方向，可选垂直或水平，默认为垂直方向
 * @param verticalArrangement 垂直方向的排列方式，默认为顶部对齐
 * @param horizontalAlignment 水平方向的对齐方式，默认为起始对齐
 * @param content 可组合的内容块
 */
@Composable
fun SodiumScrollView(modifier: Modifier = Modifier,
                     orientation: Orientation= Orientation.Vertical,
                     verticalArrangement: Arrangement.Vertical = Arrangement.Top,
                     horizontalAlignment: Alignment.Horizontal = Alignment.Start,
                     content: (@Composable () -> Unit)) {
    // 创建滚动状态并根据方向选择相应的滚动修饰符
    val scrollState: ScrollState = rememberScrollState()
    val scrollModifier: Modifier = when (orientation) {
        Orientation.Vertical   -> Modifier.verticalScroll(scrollState)
        Orientation.Horizontal -> Modifier.horizontalScroll(scrollState)
    }
    // 构建可滚动的Column容器
    Column(
        modifier = modifier.fillMaxWidth().wrapContentHeight().then(other = scrollModifier),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment

    ) {
        content.invoke()
    }
}