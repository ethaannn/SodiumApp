package com.ethan.sodium.ui.components.button
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethan.sodium.extension.debounceClickable
import com.ethan.sodium.ui.components.loading.SodiumLoading



@Immutable
class ButtonAttribute(
        val padding: PaddingValues=PaddingValues(vertical = 0.dp, horizontal = 0.dp),
        val borderRadius: Dp = 6.dp,
        val size: DpSize = DpSize(184.dp, 40.dp),
        val indication: Indication? = ripple(bounded = true, radius = Dp.Unspecified, color = Color.LightGray),
        val fontSize: TextUnit =14.sp,
        val contentAlignment: Alignment = Alignment.Center,

        val debounceTimeout: Long = 300L,

)


@Immutable
data class ButtonStateColors(
        val containerColor: Color= Color.Black,
        val contentColor: Color =Color.White,
        val containerPressColor: Color = Color.Black,
        val contentPressColor: Color = Color.White,
        val containerDisableColor: Color = Color.LightGray,
        val contentDisableColor: Color =Color.Gray
)

/**
 * 按钮
 *
 * @param text 按钮文字
 * @param type 类型
 * @param size 大小
 * @param width 宽度
 * @param disabled 是否禁用
 * @param loading 是否加载中
 * @param onClick 点击事件
 */
@Composable
fun SodiumCustomButton(
        text: String,
        modifier: Modifier = Modifier,
        attribute: ButtonAttribute = ButtonAttribute(),
        stateColor: ButtonStateColors = ButtonStateColors(),
        disabled: Boolean = false,
        loading: Boolean = false,
        onClick: ((disable: Boolean) -> Unit)? = {}) {

    val localDisabled = disabled || loading
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()


    val backgroundColor = if (localDisabled) {
        stateColor.containerDisableColor
    } else {
        if (isPressed) {
            stateColor.containerPressColor
        } else {
            stateColor.containerColor
        }
    }

    val contentColor = if (localDisabled) {
        stateColor.contentDisableColor
    } else {
        if (isPressed) {
           stateColor.contentPressColor
        } else {
            stateColor.contentColor
        }
    }

    Box(
        modifier = Modifier
            .widthIn(attribute.size.width)
            .heightIn(attribute.size.height)
            .clip(shape = RoundedCornerShape(attribute.borderRadius))
            .debounceClickable(
                debounceTimeout = attribute.debounceTimeout,
                enabled = true,
                interactionSource = interactionSource,
                indication = attribute.indication) {
                onClick?.invoke(localDisabled)
            }
            .background(backgroundColor)
            .padding(paddingValues = attribute.padding)
            .alpha(alpha = if (disabled) 0.7f else 1f)
            .then(other = modifier),
        contentAlignment =attribute.contentAlignment) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            if (loading) {
                SodiumLoading(color =contentColor)
                Spacer(Modifier.width(8.dp))
            }
            Text(text =  text, color =contentColor, fontSize = attribute.fontSize)
        }
    }
}



