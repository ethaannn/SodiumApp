package com.ethan.sodium.app.ui.screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ethan.sodium.app.domain.provider.AppLocalProvider
import com.ethan.sodium.ui.components.SodiumScrollView
import com.ethan.sodium.ui.components.button.SodiumCustomButton
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.slf4j.Logger

/**
 * 山河API 首页
 */
@Composable
fun ShanHeScreen(){
    val mLogger: Logger = AppLocalProvider.LocalLogger.current
    var inputText by remember { mutableStateOf("") }


    Box (modifier = Modifier.fillMaxSize()){
        SodiumScrollView(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            orientation = Orientation.Vertical,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            SodiumEditText(value = inputText) { value ->
                mLogger.info( "SLF4J:ShanHeScreen:SodiumEditText:value= $value")
                inputText =value
            }

            Spacer(modifier = Modifier.height(16.dp))
            SodiumCustomButton("查询手机号归属地"){
                inputText = inputText.plus("查询手机号归属地")
            }
            Text(text = "Click import logs1.")
            Text(text = "Click import logs2.")


        }
    }


}

@Composable
fun SodiumEditText(
    modifier: Modifier = Modifier,
    value: String="",
    label:String="",
    delayMillis: Long = 300L,
    result: ((value: String) -> Unit) = {}
) {
    var text by remember { mutableStateOf(value = value) }
    val pendingJob = remember { mutableStateOf<Job?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(value) {
        if (value != text) {
            text = value
        }
    }

    TextField(
        value = text,
        onValueChange = {it ->
            text = it
            pendingJob.value?.cancel()
            // 启动新的延迟任务
            pendingJob.value = scope.launch {
                delay(delayMillis) // 延迟1秒，可根据需要调整时间
                result.invoke(it)
            }
        },
        modifier = modifier.fillMaxWidth(),
        trailingIcon={
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "搜索"
            )
        },
        label = { Text(text = label) })
}
