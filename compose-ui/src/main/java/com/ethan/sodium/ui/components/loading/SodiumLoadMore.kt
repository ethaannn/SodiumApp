package com.ethan.sodium.ui.components.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethan.sodium.ui.components.divider.SodiumDivider
import com.ethan.sodium.ui.components.loading.LoadMoreType.*

enum class LoadMoreType {
    LOADING,
    EMPTY_DATA,
    ALL_LOADED
}

@Composable
fun SodiumLoadMore(modifier: Modifier = Modifier, type: LoadMoreType = LOADING, listState: LazyListState? = null) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (type) {
            LOADING    -> {
                SodiumLoading()
                Spacer(modifier = Modifier.width(width = 8.dp))
                Text(
                    text = "正在加载...", color = MaterialTheme.colorScheme.onSecondary, fontSize = 14.sp
                )

                if (listState != null) {
                    LaunchedEffect(key1 = Unit) {
                        listState.scrollToItem(listState.layoutInfo.totalItemsCount)
                    }
                }
            }

            EMPTY_DATA -> {
                SodiumDivider(modifier = Modifier.weight(weight = 1f))
                Text(
                    text = "暂无数据",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                SodiumDivider(modifier = Modifier.weight(weight = 1f))
            }

            ALL_LOADED -> {
                SodiumDivider(modifier = Modifier.weight(weight = 1f))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(size = 4.dp)
                        .background(MaterialTheme.colorScheme.outline, CircleShape)
                )
                SodiumDivider(modifier = Modifier.weight(weight = 1f))
            }
        }
    }

}