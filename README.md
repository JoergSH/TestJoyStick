# Joystick UI Component in Jetpack Compose

This project implements a customizable joystick UI component using Jetpack Compose for Android. 
The joystick features a draggable dot within a bounded area, and it supports both portrait and landscape orientations. 

## Features
- **Customizable Size**: Adjust the size of the joystick and the dot.
- **Background and Dot Images**: Use custom images for the joystick background and dot.
- **Coordinate Calculation**: The joystick calculates the x and y coordinates relative to the center of the joystick.
- **Orientation Support**: It adjusts itself based on the screen orientation (portrait or landscape).
- **Coordinate Display**: Optionally display the current joystick position (x, y coordinates).
- **Movement Callback**: A `moved` callback is triggered when the joystick is moved, providing the updated coordinates.

-  * Joystick with color selection for the dot and background
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

[Screen_recording_20250205_203412.webm](https://github.com/user-attachments/assets/4b8c6798-0596-4844-bd79-7b4218b07b17)
