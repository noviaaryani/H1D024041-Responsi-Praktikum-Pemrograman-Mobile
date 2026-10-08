package com.pemmob.pieplay.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = PastelGreen,
    onPrimary = CutePlum,
    primaryContainer = PastelGreenContainer,
    onPrimaryContainer = CutePlum,
    secondary = PastelPink,
    onSecondary = CutePlum,
    secondaryContainer = PastelPinkContainer,
    onSecondaryContainer = CutePlum,
    background = LightBackground,
    onBackground = CutePlum,
    surface = LightSurface,
    onSurface = CutePlum,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = CutePlum
)

private val DarkColorScheme = darkColorScheme(
    primary = PastelGreen,
    onPrimary = SoftDarkPlum,
    primaryContainer = PastelGreenDark,
    onPrimaryContainer = Color.White,
    secondary = PastelPink,
    onSecondary = SoftDarkPlum,
    secondaryContainer = PastelPinkDark,
    onSecondaryContainer = Color.White,
    background = DarkBackground,
    onBackground = Color(0xFFF5E6EB),
    surface = DarkSurface,
    onSurface = Color(0xFFF5E6EB),
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFF5E6EB)
)

@Composable
fun PiePlayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is set to false by default to keep the cute pastel theme active
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
