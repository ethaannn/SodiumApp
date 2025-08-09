package com.ethan.sodium.ui.components.loading

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ethan.sodium.R


@Composable
fun SodiumLoading(){

        Box (modifier = Modifier.size(64.dp)
            .background(Color.Red, shape = RoundedCornerShape(8.dp)), contentAlignment = Alignment.Center
        ){
            SodiumLoading(color = Color.Blue)

        }

}



@Composable
fun SodiumLoading(size: Dp = 16.dp, color: Color = Color.Unspecified,
                  isRotating: Boolean = true,
                  defaultResId: Int = R.drawable.sodium_loading, contentDescription: String="loading") {
    val angle by if (isRotating) {
        val transition = rememberInfiniteTransition(label = "")
        transition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                tween(durationMillis = 1000, easing = LinearEasing),
                RepeatMode.Restart
            ),
            label = "SodiumLoadingAnimation"
        )
    } else {
        remember { mutableFloatStateOf(0f) }
    }

    Icon(
        painter = painterResource(id = defaultResId),
        contentDescription = contentDescription,
        modifier = Modifier
            .size(size)
            .rotate(angle),
        tint = color
    )
}



//@Composable
//fun SodiumLoading(color: Color = MaterialTheme.colorScheme.inversePrimary, animationSpec: DurationBasedAnimationSpec<Float> = tween(durationMillis = 1000)) {
//    val infiniteTransition = rememberInfiniteTransition(label = "")
//    val currentIndex by infiniteTransition.animateFloat(
//        initialValue = 0f,
//        targetValue = 2f,
//        animationSpec = infiniteRepeatable(
//            animation = animationSpec,
//            repeatMode = RepeatMode.Restart
//        ),
//        label = "WeLoadingMPAnimation"
//    )
//
//    Canvas(modifier = Modifier.size(width = 44.dp, height = 20.dp)) {
//        val dotRadius = 4.dp.toPx()
//        val spacing = (size.width - 2 * dotRadius) / 2
//
//        repeat(3) { index ->
//            val isActive = index == currentIndex.roundToInt()
//            val dotColor = color.copy(alpha = if (isActive) 0.8f else 0.4f)
//            val center = Offset(
//                x = dotRadius + spacing * index,
//                y = size.height / 2
//            )
//
//            drawCircle(
//                color = dotColor,
//                radius = dotRadius,
//                center = center
//            )
//        }
//    }
//}


@Preview(name = "SodiumLoading")
@Composable
private fun PreviewWidget() {
//    SodiumLoading()
    SodiumLoading(color = Color.Red)
    SodiumLoading()
}



