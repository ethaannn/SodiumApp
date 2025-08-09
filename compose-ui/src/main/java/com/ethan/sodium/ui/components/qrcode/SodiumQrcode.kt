package com.ethan.sodium.ui.components.qrcode

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.ethan.sodium.extension.dp2px

import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.graphics.Color as AndroidColor
@Composable
fun WeQrCodeGenerator(
    content: String,
    size: Dp =180.dp,
    color: Color = Color.Black,
    icon: ImageBitmap? = null,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val bitmap = produceState<ImageBitmap?>(initialValue = null, key1 = content, key2 = size, key3 = icon) {
        value = withContext(Dispatchers.IO) {
            generateQrCode(content,
                size.dp2px(density)
                    .toInt(),
                color.toArgb()).asImageBitmap()
        }
    }


    val sizePx = size.dp2px(density).toInt()
    Box(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(12.dp))

    ) {
        bitmap.value?.let { qrBitmap ->
            Canvas(modifier = Modifier.size(size)) {
                // 绘制二维码
                drawImage(
                    image = qrBitmap,
                    dstSize = IntSize(sizePx, sizePx)
                )

                // 绘制中央图标（如果提供）
                icon?.let { iconBitmap ->
                    val iconSize = sizePx / 5 // 图标大小为二维码的1/5
                    val iconOffset = (sizePx - iconSize) / 2

                    drawImage(
                        image = iconBitmap,
                        dstOffset = IntOffset(iconOffset, iconOffset),
                        dstSize = IntSize(iconSize, iconSize)
                    )
                }
            }
        }
    }
}

private fun generateQrCode(content: String, size: Int, color: Int): Bitmap {
    // 增加实际生成尺寸以留出边距空间
    val actualSize = (size * 0.96).toInt() // 实际二维码占据80%空间
    val margin = (size * 0.02).toInt()     // 边距为10%

    val hints = mapOf(
        EncodeHintType.MARGIN to 1,// ZXing库的内置边距
        EncodeHintType.CHARACTER_SET to "UTF-8" // 指定字符集为 UTF-8
    )
    val bitMatrix = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, actualSize, actualSize, hints)

    // 创建带边距的Bitmap
    val pixels = IntArray(size * size) { pos ->
        val x = pos % size
        val y = pos / size

        // 检查是否在二维码绘制区域内
        if (x >= margin && x < size - margin && y >= margin && y < size - margin) {
            // 在二维码区域内
            if (bitMatrix.get(x - margin, y - margin)) {
                color
            } else {
                AndroidColor.TRANSPARENT
            }
        } else {
            // 边距区域保持透明
            AndroidColor.TRANSPARENT
        }
    }

    return Bitmap.createBitmap(pixels, size, size, Bitmap.Config.ARGB_8888)
}