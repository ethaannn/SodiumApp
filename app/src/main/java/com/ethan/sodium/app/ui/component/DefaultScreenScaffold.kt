package com.ethan.sodium.app.ui.component;

import androidx.annotation.DrawableRes;
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethan.sodium.app.R
import com.ethan.sodium.app.ui.theme.PurpleGrey40
import com.ethan.sodium.ui.components.SodiumScrollView

@Composable
fun DefaultScreenScaffold(
    title: String = "SodiumUI",
    @DrawableRes id: Int = R.mipmap.app_icon,
    content: @Composable () -> Unit
                         ) {
    Column(modifier = Modifier.fillMaxSize()
        .statusBarsPadding()) {

        Spacer(modifier = Modifier.height(height = 32.dp))
        Row(modifier = Modifier.background(color = PurpleGrey40)
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(height = 100.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = id), contentDescription = "", modifier = Modifier.size(size = 32.dp))
            Spacer(modifier = Modifier.width(width = 16.dp))


            Column {
                Text(text = title, fontSize = 28.sp)
                Text(text = title, fontSize = 24.sp)
            }

        }

        Spacer(modifier = Modifier.height(height = 32.dp))

        SodiumScrollView(modifier = Modifier.fillMaxWidth()
            .wrapContentHeight(), content = content)

    }
}