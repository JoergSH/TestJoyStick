package com.example.testjoystick

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testjoystick.ui.JoyStick

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Testit()
            }
        }
    }

@Composable
fun Testit() {
    JoyStick(
        Modifier.padding(100.dp),
        size = 140.dp,
        dotSize = 40.dp,
        backgroundImage = R.drawable.arrow,
        dotImage = R.drawable.joystick_dot_1,
        backgroundColor = Color.Red,
        dotColor = Color.Black,
        coordinateTextColor = Color.Black,
        coordinateTextSize = 14.sp,
        showCoordinates = true,
        max = 90


    ) { x: Int, y: Int ->
        Log.d("JoyStick", "$x, $y")
    }
}
