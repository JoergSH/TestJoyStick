package com.example.testjoystick.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testjoystick.R
import kotlin.math.atan
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * Joystick with color selection for the dot and background
 * @param size Joystick size
 * @param dotSize Joystick dot size
 * @param backgroundImage Joystick background image
 * @param dotImage Joystick dot image
 * @param backgroundColor Color of the joystick background
 * @param dotColor Color of the joystick dot
 * @param coordinateTextColor Color of the coordinate text
 * @param coordinateTextSize Size of the coordinate text
 * @param showCoordinates Control whether to display the coordinates
 * @param max Maximum value for x/y
 * @param moved Callback for joystick movement
 */
@Composable
fun JoyStick(
    modifier: Modifier = Modifier,
    size: Dp = 170.dp,
    dotSize: Dp = 40.dp,
    backgroundImage: Int = R.drawable.joystick_background_1,
    dotImage: Int = R.drawable.joystick_dot_1,
    backgroundColor: Color = Color.Cyan,
    dotColor: Color = Color.Red,
    coordinateTextColor: Color = Color.Black,
    coordinateTextSize: TextUnit = 10.sp,
    showCoordinates: Boolean = true,
    max: Int = 90,
    moved: (Int, Int) -> Unit = { _, _ -> }
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Box(
        modifier = modifier
            .size(size)
    ) {
        val density = LocalDensity.current.density
        val sizePx = size.value * density
        val dotSizePx = dotSize.value * density
        val maxOffset = (sizePx - dotSizePx) / 2f // Maximum offset for the dot
        val centerX = sizePx / 2f
        val centerY = sizePx / 2f

        var positionX by remember { mutableFloatStateOf(centerX) }
        var positionY by remember { mutableFloatStateOf(centerY) }

        var radius by remember { mutableFloatStateOf(0f) }
        var theta by remember { mutableFloatStateOf(0f) }

        var xPos by remember { mutableIntStateOf(0) }
        var yPos by remember { mutableIntStateOf(0) }

        // Background image with color filter
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = "JoyStickBackground",
            modifier = Modifier.size(size),
            colorFilter = ColorFilter.tint(backgroundColor)
        )

        // Joystick dot with color filter
        Image(
            painter = painterResource(id = dotImage),
            contentDescription = "JoyStickDot",
            modifier = Modifier
                .offset {
                    IntOffset(
                        (positionX - dotSizePx / 2).roundToInt(),
                        (positionY - dotSizePx / 2).roundToInt()
                    )
                }
                .size(dotSize)
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragEnd = {
                            // When the drag ends, reset the dot to the center
                            positionX = centerX
                            positionY = centerY
                            // Also reset the coordinates when the finger is lifted
                            xPos = 0
                            yPos = 0
                            moved(xPos, yPos)
                        }
                    ) { pointerInputChange: PointerInputChange, offset: Offset ->
                        // Calculate the new position based on the offset
                        val newX = positionX + offset.x
                        val newY = positionY + offset.y

                        // Constrain the dot's movement within the maximum range
                        val boundedX = newX.coerceIn(centerX - maxOffset, centerX + maxOffset)
                        val boundedY = newY.coerceIn(centerY - maxOffset, centerY + maxOffset)

                        // Set the new position of the dot
                        positionX = boundedX
                        positionY = boundedY

                        // Calculate the coordinates relative to the center
                        val deltaX = positionX - centerX
                        val deltaY = positionY - centerY

                        // Calculate the radius and angle for the joystick
                        radius = sqrt(deltaX.pow(2) + deltaY.pow(2))
                        theta = atan(deltaY / deltaX)

                        // Calculate joystick positions in degrees (for x and y)
                        xPos = (deltaX / maxOffset * max).roundToInt()
                        yPos = (deltaY / maxOffset * max).roundToInt()
                        if (isLandscape) xPos = yPos.also { yPos = xPos }
                        moved(xPos, yPos)
                    }
                },
            colorFilter = ColorFilter.tint(dotColor)
        )

        // Coordinate display when enabled
        if (showCoordinates) {
            Text(
                text = "${(xPos)}",
                modifier = Modifier
                    .align(if (isLandscape) Alignment.TopEnd else Alignment.TopStart)
                    .padding(1.dp),
                color = coordinateTextColor,
                style = TextStyle(fontSize = coordinateTextSize)
            )

            Text(
                text = "${(yPos)}",
                modifier = Modifier
                    .align(if (isLandscape) Alignment.TopStart else Alignment.TopEnd)
                    .padding(1.dp),
                color = coordinateTextColor,
                style = TextStyle(fontSize = coordinateTextSize)
            )
        }
    }
}
