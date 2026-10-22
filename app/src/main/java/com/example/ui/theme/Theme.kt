package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CyberColorScheme = darkColorScheme(
    primary = ElectricIndigoLight,
    onPrimary = Color(0xFF1000A9),
    primaryContainer = ElectricIndigo,
    onPrimaryContainer = Color.White,
    secondary = CyberTealLight,
    onSecondary = Color(0xFF003640),
    secondaryContainer = CyberTeal,
    onSecondaryContainer = Color.White,
    tertiary = DefenseGreenLight,
    onTertiary = Color(0xFF003824),
    tertiaryContainer = DefenseGreen,
    onTertiaryContainer = Color.White,
    background = CyberVoid,
    onBackground = TextPrimary,
    surface = CyberSurfaceElevated,
    onSurface = TextPrimary,
    surfaceVariant = CyberSurfaceContainer,
    onSurfaceVariant = TextSecondary,
    outline = CyberBorderGlass,
    outlineVariant = CyberBorderSubtle
)

@Composable
fun AlexArantesTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CyberColorScheme,
        typography = Typography,
        content = content
    )
}
