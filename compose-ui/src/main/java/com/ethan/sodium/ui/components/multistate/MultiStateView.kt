package com.ethan.sodium.ui.components.multistate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ethan.sodium.R
import com.ethan.sodium.ui.viewmodel.BaseCoreViewModel

@Composable
fun <T> MultiStateView(
    modifier: Modifier = Modifier,
    viewModel:  BaseCoreViewModel,           // 当前状态（必须）
    data: T? = null,               // 数据对象（可选）
    content: @Composable (T?) -> Unit, // SHOW 状态内容（必须）

    // 可选状态视图（带默认值）
    emptyContent: @Composable (BoxScope.() -> Unit) = {
        DefaultEmptyView()
    },
    loadingContent: @Composable (BoxScope.() -> Unit) = {
        DefaultLoading()
    },
    onButtonClick: () -> Unit = {},
    errorContent: @Composable (BoxScope.() -> Unit) = {
        DefaultErrorView() {
            onButtonClick.invoke()
        }
    },

    ) {
    val state: MultiState by viewModel.multistate.collectAsState()
    Box(modifier = modifier) {
        when (state) {
            MultiState.EMPTY   -> emptyContent()
            MultiState.LOADING -> loadingContent()
            MultiState.ERROR   -> errorContent()
            MultiState.CONTENT -> content(data)
        }
    }
}


@Composable
fun DefaultEmptyView(
    modifier: Modifier = Modifier,
    text: String = "暂无数据",
    buttonText: String = "重试",
    onButtonClick: () -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = onButtonClick
            ) {
                Text(text = buttonText)
            }
        }
    }
}

@Composable
fun DefaultLoading(
    modifier: Modifier = Modifier,
    loadingText: String = "加载中..."
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
            Text(
                text = loadingText,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}


@Composable
fun DefaultErrorView(
    modifier: Modifier = Modifier,
    errorText: String = "加载失败",
    buttonText: String = "重试",
    onRetryClick: () -> Unit = {}
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 错误图片 - 你可以根据实际资源文件调整
            Image(
                painter = painterResource(id = R.drawable.sodium_loading), // 请替换为实际的错误图标资源
                contentDescription = "错误",
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 16.dp)
            )

            // 错误文本
            Text(
                text = errorText,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // 重试按钮
            Button(
                onClick = onRetryClick
            ) {
                Text(text = buttonText)
            }
        }
    }
}