package com.ethan.sodium.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ripple
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ethan.sodium.app.data.model.User
import com.ethan.sodium.app.domain.provider.AppLocalProvider
import com.ethan.sodium.app.ui.base.ActivityBusinessBase
import com.ethan.sodium.app.ui.navigation.AppNavHost
import com.ethan.sodium.app.ui.theme.SodiumAppTheme
import com.ethan.sodium.app.ui.viewmodel.AppViewModel
import com.ethan.sodium.extension.fromJson
import com.ethan.sodium.extension.toJson
import com.ethan.sodium.ui.components.button.ButtonAttribute
import com.ethan.sodium.ui.components.button.ButtonStateColors
import com.ethan.sodium.ui.theme.BackgroundOctonaryColor
import com.ethan.sodium.ui.theme.BackgroundQuaternaryColor
import com.ethan.sodium.ui.theme.BackgroundQuinaryColor
import com.ethan.sodium.ui.theme.BackgroundSenaryColor
import com.ethan.sodium.ui.theme.BackgroundTertiaryColor
import com.ethan.sodium.ui.theme.ForegroundOctonaryColor
import com.ethan.sodium.ui.theme.ForegroundSecondaryColor
import com.squareup.moshi.Moshi
import org.koin.android.ext.android.inject

class MainActivity : ActivityBusinessBase() {

    val moshi: Moshi by inject()
    val mAppViewModel: AppViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            CompositionLocalProvider(
                values = arrayOf(
                    AppLocalProvider.LocalLogger provides mLogger,
                    AppLocalProvider.LocalNavController provides navController
                )
            ) {
                SodiumAppTheme {
                    Scaffold(
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding: PaddingValues ->
                        AppNavHost(
                            innerPadding = innerPadding,
                            navHostController = navController)
                    }
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        val user = User("1202613108", "Ethan")
        val json = moshi.toJson(any = user)
        mLogger.info("SLF4J:json: $json")
        val user2 =moshi.fromJson<User>(json =  json)
        mLogger.info("SLF4J:json: $user2")


        val map: Map<String, Any> = mapOf("name" to "Ethan", "age" to 18)
        val mapJson=moshi.toJson(map)
        mLogger.info("SLF4J:map: $mapJson")
        val jsonToMap:Map<String,Any >? =moshi.fromJson<Map<String, Any>>(mapJson)
        mLogger.info("SLF4J:jsonToMap:age: ${jsonToMap?.javaClass?.name}")
    }
}




val BUTTON_PRIMARY = ButtonStateColors(
    containerColor = BackgroundOctonaryColor,
    contentColor = ForegroundOctonaryColor,
    containerPressColor = BackgroundOctonaryColor,
    contentPressColor = ForegroundOctonaryColor,
    containerDisableColor = BackgroundQuinaryColor,
    contentDisableColor = ForegroundSecondaryColor)

val BUTTON_SECONDARY =ButtonStateColors(
    containerColor = BackgroundQuaternaryColor,
    contentColor = ForegroundSecondaryColor,
    containerPressColor = BackgroundTertiaryColor,
    contentPressColor = ForegroundSecondaryColor,
    containerDisableColor = BackgroundQuaternaryColor,
    contentDisableColor = ForegroundSecondaryColor)



val LARGE: ButtonAttribute = ButtonAttribute(
    padding = PaddingValues(
        vertical = 0.dp,
        horizontal = 0.dp),
    borderRadius = 16.dp,
    size = DpSize(
        184.dp,
        36.dp),
    indication = ripple(
        bounded = true,
        radius = Dp.Unspecified,
        color = Color.White),
    fontSize = 14.sp)

val MEDIUM: ButtonAttribute = ButtonAttribute(padding = PaddingValues(vertical = 10.dp, horizontal = 24.dp), fontSize = 14.sp)


val SMALL: ButtonAttribute = ButtonAttribute(
    padding = PaddingValues(
        vertical = 6.dp,
        horizontal = 12.dp),
    borderRadius = 6.dp,
    fontSize = 14.sp,
    indication = ripple(
        bounded = true,
        radius = Dp.Unspecified,
        color = BackgroundSenaryColor),
)
