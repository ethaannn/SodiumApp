package com.ethan.sodium.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ethan.sodium.app.R
import com.ethan.sodium.app.data.MenuDataProvider
import com.ethan.sodium.app.data.model.MenuGroup
import com.ethan.sodium.app.data.model.MenuItem
import com.ethan.sodium.app.domain.provider.AppLocalProvider
import com.ethan.sodium.extension.debounceClickable
import com.ethan.sodium.ui.components.divider.SodiumDivider

@Composable
fun HomeScreen() {
    var current by rememberSaveable { mutableStateOf<Int?>(value = null) }
    val onNavigateTo: NavController = AppLocalProvider.LocalNavController.current
    LazyColumn(modifier = Modifier.fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
        .statusBarsPadding()
        .padding(horizontal = 16.dp),
               verticalArrangement = Arrangement.spacedBy(space = 8.dp)) {

        item { HomeHeader() }

        itemsIndexed(items = MenuDataProvider.menuGroups) { index: Int, menuGroup: MenuGroup ->
            MenuGroupView(group = menuGroup,
                          expanded = index == current,
                          onNavigateTo = { route: String -> onNavigateTo.navigate(route = route) }) {
                current = if (current == index) null else index
            }
        }

        item {
            Spacer(modifier = Modifier.height(height = 60.dp))
            HomeFooter()
        }

    }

}


@Composable
private fun HomeHeader() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
       Row(verticalAlignment = Alignment.CenterVertically) {
           Image(painter = painterResource(id = R.mipmap.app_icon),
                 contentDescription = "SodiumUI",
                 modifier = Modifier.height(height = 28.dp))
           Spacer(modifier = Modifier.width(width = 8.dp))
           Text(text = "SodiumUI",
               fontSize = 21.sp)
       }
        Spacer(modifier = Modifier.height(height = 19.dp))
        Text(text = "SodiumUI 是一套同微信原生视觉体验一致的基础样式库，由微信官方设计团队为微信内网页和微信小程序量身设计，令用户的使用感知更加统一。",
             fontSize = 14.sp)
        Spacer(modifier = Modifier.height(height = 16.dp))
    }
}

@Composable
private fun HomeFooter() {
    Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 40.dp)) {
        Image(painter = painterResource(id = R.drawable.ic_footer_link),
              contentDescription = null,
              colorFilter = ColorFilter.tint(Color.Black),
              modifier = Modifier.size(84.dp,
                                       19.dp))
    }
}

@Composable
private fun MenuGroupView(
    group: MenuGroup,
    expanded: Boolean,
    onNavigateTo: (route: String) -> Unit,
    onToggleExpand: () -> Unit) {
    Column(Modifier.clip(RoundedCornerShape(4.dp))
               .background(color = Color.Gray)) {
        MenuGroupHeader(group,
                        expanded) {
            if (group.path != null) {
                onNavigateTo(group.path)
            } else {
                onToggleExpand()
            }
        }
        if (group.children != null) {
            AnimatedVisibility(visible = expanded) {
                Column {
                    val children = remember { group.children.sortedBy { it.label } }
                    children.forEachIndexed { index, item ->
                        MenuGroupItem(item,
                                      onNavigateTo)
                        if (index < group.children.lastIndex) {
                            SodiumDivider(Modifier.padding(horizontal = 20.dp),
                                          colors = arrayListOf(Color.Black))
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun MenuGroupHeader(
    group: MenuGroup,
    expanded: Boolean,
    onClick: () -> Unit) {
    Row(Modifier.alpha(if (expanded) 0.5f else 1f)
            .debounceClickable { onClick() }
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = group.title,
             color = Color.Black,
             fontSize = 17.sp,
             modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = group.iconId),
              contentDescription = null,
              modifier = Modifier.size(size = 30.dp))
    }

}

@Composable
private fun MenuGroupItem(
    item: MenuItem,
    onNavigateTo: (route: String) -> Unit) {
    Row(Modifier.clickable {
            onNavigateTo(item.route)
        }
            .padding(horizontal = 20.dp,
                     vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(text = item.label,
             color = Color.Black,
             fontSize = 17.sp,
             modifier = Modifier.weight(1f))
        Icon(painter = painterResource(id = R.drawable.ic_arrow_right),
             contentDescription = null,
             tint = Color.Black)
    }
}