package com.ethan.sodium.app.domain.provider

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.slf4j.Logger

object AppLocalProvider {
    val LocalLogger = staticCompositionLocalOf<Logger> {
        error("Unable to provide a LocalLogger instance")
    }
    val LocalNavController = staticCompositionLocalOf<NavHostController> {
        error("NavController not provided")
    }
}