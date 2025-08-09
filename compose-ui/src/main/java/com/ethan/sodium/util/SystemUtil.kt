package com.ethan.sodium.util

import android.content.Intent
import android.content.IntentFilter
import android.graphics.Rect
import android.os.BatteryManager
import android.view.ViewTreeObserver
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ethan.sodium.extension.px2dp
import kotlin.math.roundToInt

/**
 * 软键盘高度
 */
@Composable
fun rememberKeyboardHeight(): Dp {

    val view = LocalView.current
    val density = LocalDensity.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var height by remember { mutableStateOf(0.dp) }

    DisposableEffect(keyboardController) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val windowInsets = ViewCompat.getRootWindowInsets(view)
            val bottomInset = windowInsets?.getInsets(WindowInsetsCompat.Type.systemBars())?.bottom ?: 0
            val keyboardHeight = view.height - rect.bottom - bottomInset
            height = density.run {
                keyboardHeight.toDp()
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    return height
}


@Composable
fun rememberStatusBarHeight(): Dp {
    val density = LocalDensity.current
    val statusBars = WindowInsets.statusBars

    return remember {
        with(receiver = density) {
            statusBars.getTop(this).toDp()
        }
    }
}

@Composable
fun rememberScreenWidth(): Dp {
    val configuration = LocalConfiguration.current
    LocalWindowInfo.current.containerSize.width
    return remember {
        configuration.screenWidthDp.dp
    }
}

/**
 * 获取屏吗宽度，单位px,像素
 */
@Composable
fun rememberContainerWidth(): Int = LocalWindowInfo.current.containerSize.width

/**
 * 获取高度，单位PX
 */
@Composable
fun rememberContainerHeight(): Dp = LocalWindowInfo.current.containerSize.height.px2dp(density = LocalDensity.current)

@Composable
fun rememberScreenWidthToPx(fraction: Float = 0.6f): Int {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    return remember {
        with(receiver = density) {
            (configuration.screenWidthDp * fraction).dp.toPx().roundToInt()
        }
    }
}

@Composable
fun rememberNavigationBarHeight(): Dp {
    val density = LocalDensity.current

    val navigationBars = WindowInsets.navigationBars
    return remember {
        with(receiver = density) {
           navigationBars.getBottom(this).toDp()
        }
    }
}

@Composable
fun rememberSystemBarHeight(): Dp {
    val density = LocalDensity.current
    val systemBar = WindowInsets.systemBars
    return remember {
        with(receiver = density) {
            systemBar.getBottom(this).toDp()
        }
    }
}


@Composable
fun rememberBatteryInfo(): BatteryInfo {
    val batteryStatus = LocalContext.current.registerReceiver(
        null, IntentFilter(Intent.ACTION_BATTERY_CHANGED)
    )

    val level by remember {
        derivedStateOf {
            val level = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, 0) ?: 0
            val scale = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, 0) ?: 0
            (level / scale.toFloat() * 100).toInt()
        }
    }
    val isCharging by remember {
        derivedStateOf {
            val status = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
            status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
        }
    }

    return BatteryInfo(level, isCharging)
}

data class BatteryInfo(val level: Int, val isCharging: Boolean)


