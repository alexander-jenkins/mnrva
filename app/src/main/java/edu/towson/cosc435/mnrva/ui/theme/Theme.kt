package edu.towson.cosc435.mnrva.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
//TODO: Reconfiguring theme to new colors added to Color.kt
//added a few, but have to see if theres a way to add a "tertiary" color
private val ColorPalette = darkColors(
    primary = Scandal,
    primaryVariant = Submarine,
    secondary = FringyFlower,
    secondaryVariant = Envy,
    background = Background

)

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */

@Composable
fun MNRVATheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = ColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}