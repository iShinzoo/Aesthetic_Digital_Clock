package com.example.aestheticdigitalclock

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aestheticdigitalclock.ui.theme.AestheticDigitalClockTheme
import com.example.aestheticdigitalclock.ui.theme.digitalFamily
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.SimpleTimeZone

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {
            AestheticDigitalClockTheme {
                AestheticDigitalClock()
            }
        }
    }
}

@Composable
fun AestheticDigitalClock() {
    var  hour by remember { mutableStateOf("0") }
    var  minutes by remember { mutableStateOf("0") }
    var  seconds by remember { mutableStateOf("0") }
    var  amORpm by remember { mutableStateOf("0") }

    LaunchedEffect(Unit){
        while (true){
            val cal = android.icu.util.Calendar.getInstance()
            hour = cal.get(Calendar.HOUR).run {
                if (this.toString().length == 1) "0$this" else "$this"
            }
            minutes = cal.get(Calendar.MINUTE).run {
                if (this.toString().length == 1) "0$this" else "$this"
            }
            seconds = cal.get(Calendar.SECOND).run {
                if (this.toString().length == 1) "0$this" else "$this"
            }
            amORpm = if (cal.get(Calendar.AM_PM) == Calendar.AM) "am" else "pm"

            delay(1000)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){

        Row(modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
            ){
            Text(text = "$hour:$minutes:$seconds",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 200.sp,
                fontFamily = digitalFamily
            )
            Text(text = " $amORpm",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 100.sp,
                fontFamily = digitalFamily,
                modifier = Modifier.padding(top = 60.dp)
            )
        }


    }



}

@Preview(showBackground = true)
@Composable
fun PreviewAestheticDigitalClock() {
    AestheticDigitalClockTheme {
        AestheticDigitalClock()
    }
}



