package com.ethan.sodium.ui.components.divider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ethan.sodium.extension.dp2px

/**
 * 线条类型枚举类
 *
 * 定义了两种线条样式：实线和虚线
 */
@Immutable
enum class Types {
    Solid,
    Dashed,
}

/**
 * 自定义分割线组件，支持多种样式和方向
 *
 * @param modifier 修饰符，用于设置分割线的布局和外观属性
 * @param orientation 分割线方向，默认为水平方向
 * @param thickness 分割线厚度，默认使用系统默认值
 * @param colors 分割线颜色列表，支持渐变效果，默认为系统默认颜色
 * @param type 分割线类型，默认为实线
 * @param dashWidth 虚线段长度，仅在虚线类型时生效
 * @param gapWidth 间隔长度，仅在虚线类型时生效
 */
@Composable
fun SodiumDivider(
    modifier: Modifier,
    orientation: Orientation = Orientation.Horizontal,
    thickness: Dp = DividerDefaults.Thickness,
    colors: List<Color> = listOf(DividerDefaults.color),
    type: Types = Types.Solid,
    dashWidth: Dp = 4.dp, // 虚线段长度
    gapWidth: Dp = 4.dp // 间隔长度
) {
    val density: Density = LocalDensity.current

    val pathEffect: PathEffect? = when (type) {
        Types.Solid  -> null
        Types.Dashed -> PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashWidth.dp2px(density = density), gapWidth.dp2px(density = density)),
            phase = 0f
        )
    }
    val tempColors = if (colors.isNotEmpty() && colors.size< 2 ) colors.plus(colors) else colors

    val gradientBrush = Brush.linearGradient(colors = tempColors)

    if (orientation == Orientation.Horizontal) {

        Canvas(
            modifier = modifier
                .fillMaxWidth()
                .height(height = thickness)
        ) {
            drawLine(
                brush = gradientBrush,
                start = Offset(x = 0f, y = thickness.toPx() / 2),
                end = Offset(x = size.width, y = thickness.toPx() / 2),
                strokeWidth = thickness.toPx(),
                pathEffect = pathEffect,

                )
        }
    } else {
        // 垂直虚线实现
        Canvas(
            modifier = modifier
                .width(thickness)
                .fillMaxHeight()
        ) {
            drawLine(
                brush = gradientBrush,
                start = Offset(x = thickness.toPx() / 2, y = 0f),
                end = Offset(x = thickness.toPx() / 2, y = size.height),
                strokeWidth = thickness.toPx(),
                pathEffect = pathEffect
            )
        }
    }
}