package com.ethan.sodium.app.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ethan.sodium.app.ui.HomeScreen
import com.ethan.sodium.app.ui.screen.MainScreen
import com.ethan.sodium.app.ui.screen.MultiStateScreen
import com.ethan.sodium.app.ui.screen.ShanHeScreen

@Composable
fun AppNavHost(innerPadding: PaddingValues,navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = AppDestination.HOME_SCREEN,
        modifier = Modifier.padding(paddingValues = innerPadding)
    ){
        composable<AppDestination.MAIN_SCREEN> {
            MainScreen(name = "MainPage")
        }
        composable<AppDestination.MAIN_SHAN_HE_SCREEN>{
            ShanHeScreen()
        }
        composable<AppDestination.SCREEN_MULTI_STATE> {
            MultiStateScreen()
        }
        composable<AppDestination.HOME_SCREEN> {
            HomeScreen()
        }

    }

}
