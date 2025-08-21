package com.ethan.sodium.app.ui.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ethan.sodium.app.LARGE
import com.ethan.sodium.app.SMALL
import com.ethan.sodium.app.common.log.ILoggerManager
import com.ethan.sodium.app.common.log.ImplLoggerManager
import com.ethan.sodium.app.domain.provider.AppLocalProvider
import com.ethan.sodium.app.ui.navigation.AppDestination
import com.ethan.sodium.app.ui.theme.SodiumAppTheme
import com.ethan.sodium.ui.components.button.ButtonAttribute
import com.ethan.sodium.ui.components.button.SodiumCustomButton
import com.ethan.sodium.ui.components.loading.SodiumLoading
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.log


@Composable
fun MainScreen(name: String, modifier: Modifier = Modifier) {
    val mContext: Context=LocalContext.current
    val mLogger: Logger = LoggerFactory.getLogger(LocalContext.current.javaClass)
        val navHostController:NavHostController = AppLocalProvider.LocalNavController.current

    val mDisable: MutableState<Boolean> = remember{ mutableStateOf(value = false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Hello $name!",
            modifier = modifier)
        SodiumLoading(
            size = 64.dp,
            color = Color.Red)
        Spacer(modifier = Modifier.height(height = 20.dp))
        SodiumCustomButton(
            text = "山河API",
            attribute = LARGE,
            loading = false,
            disabled = mDisable.value) { disable: Boolean ->
            navHostController.navigate(AppDestination.MAIN_SHAN_HE_SCREEN)
        }

        Spacer(modifier = Modifier.height(height = 20.dp))

        SodiumCustomButton(
            text = "Button1",
            attribute = SMALL,
            loading = false,
            disabled = false) { disable: Boolean ->
            println("LOG4J:disable-> $disable")
        }
        Spacer(modifier = Modifier.height(height = 20.dp))
        SodiumCustomButton(text = "MatchParent Button",

            attribute = ButtonAttribute(
                padding = PaddingValues(vertical = 0.dp, horizontal = 20.dp),
                borderRadius=18.dp,
                size = DpSize(width = 200.dp, height = 40.dp),
                indication = ripple(bounded = true, radius = Dp.Unspecified, color = Color.Red),
                fontSize = 14.sp),
            modifier= Modifier
                .border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(size = 22.dp))
                .padding(horizontal = 30.dp)
                .fillMaxWidth(),
        )



        Spacer(modifier = Modifier.height(height = 20.dp))
        SodiumCustomButton(text = "MatchParent Button"){
            println("aaaaaaaaaa")
        }
        Spacer(modifier = Modifier.height(height = 20.dp))
        SodiumCustomButton(text = "MultiStateView"){
            mLogger.debug("MultiStateView")
            navHostController.navigate(AppDestination.SCREEN_MULTI_STATE)
        }
        val mLogger = LoggerFactory.getLogger(LocalContext.current.javaClass)
        val context = LocalContext.current
        Button(onClick = {
            val iLoggerManager: ILoggerManager = ImplLoggerManager(mContext = context)
            mLogger.info("SLF4J::Greeting: iLoggerManager: ${iLoggerManager.hashCode()}")
            val uri: Uri = iLoggerManager.zip()
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "application/zip"
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        },modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()){
            Text(text = "Click import logs")
        }

    }

}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SodiumAppTheme {
        MainScreen("Android")
    }
}