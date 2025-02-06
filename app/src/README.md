# Joystick UI Component in Jetpack Compose

This project implements a customizable joystick UI component using Jetpack Compose for Android. The joystick features a draggable dot within a bounded area, and it supports both portrait and landscape orientations. 

## Features
- **Customizable Size**: Adjust the size of the joystick and the dot.
- **Background and Dot Images**: Use custom images for the joystick background and dot.
- **Coordinate Calculation**: The joystick calculates the x and y coordinates relative to the center of the joystick.
- **Orientation Support**: It adjusts itself based on the screen orientation (portrait or landscape).
- **Coordinate Display**: Optionally display the current joystick position (x, y coordinates).
- **Movement Callback**: A `moved` callback is triggered when the joystick is moved, providing the updated coordinates.

## Installation

Clone this repository or add the `JoyStick` composable to your project.

```bash
git clone https://github.com/yourusername/joystick-compose.git
