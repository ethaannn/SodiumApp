package com.ethan.sodium.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
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
import com.ethan.sodium.app.common.log.ILoggerManager
import com.ethan.sodium.app.common.log.ImplLoggerManager
import com.ethan.sodium.ui.components.loading.SodiumLoading
import com.ethan.sodium.app.ui.theme.SodiumAppTheme
import com.ethan.sodium.ui.components.button.ButtonAttribute
import com.ethan.sodium.ui.components.button.ButtonStateColors
import com.ethan.sodium.ui.components.button.SodiumCustomButton
import com.ethan.sodium.ui.theme.BackgroundOctonaryColor
import com.ethan.sodium.ui.theme.BackgroundQuaternaryColor
import com.ethan.sodium.ui.theme.BackgroundQuinaryColor
import com.ethan.sodium.ui.theme.BackgroundSenaryColor
import com.ethan.sodium.ui.theme.BackgroundTertiaryColor
import com.ethan.sodium.ui.theme.ForegroundOctonaryColor
import com.ethan.sodium.ui.theme.ForegroundSecondaryColor
import org.koin.java.KoinJavaComponent.inject
import org.slf4j.LoggerFactory
import kotlin.getValue
import kotlin.hashCode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SodiumAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val mContext=LocalContext.current
    val mDisable = remember{ mutableStateOf(false) }
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
            text = "Button1",
            attribute = LARGE,
            loading = false,
            disabled = mDisable.value) { disable: Boolean ->
            println("LOG4J:disable-> $disable     ->>>>>>> ${mDisable.value.not()}")
            mDisable.value =!mDisable.value

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
            modifier= Modifier.border(width = 2.dp, color = Color.Red, shape = RoundedCornerShape(size = 22.dp)).padding(horizontal = 30.dp).fillMaxWidth(),
            )



        Spacer(modifier = Modifier.height(height = 20.dp))
        SodiumCustomButton(text = "MatchParent Button"){
            println("aaaaaaaaaa")
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

        },modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()){
            Text(text = "Click import logs")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SodiumAppTheme {
        Greeting("Android")
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
