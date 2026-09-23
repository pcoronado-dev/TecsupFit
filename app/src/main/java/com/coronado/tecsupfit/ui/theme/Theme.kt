package com.coronado.tecsupfit.ui.theme

import android.app.Activity
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
    primary = VerdeOscuro,
    onPrimary = Color.White,
    primaryContainer = VerdeClaroFondo,
    onPrimaryContainer = VerdeOscuro,
    secondary = VerdeOscuro,
    onSecondary = Color.White,
    secondaryContainer = GrisFondoTarjeta,
    onSecondaryContainer = TextoPrincipal,
    surface = Blanco,
    onSurface = TextoPrincipal,
    surfaceVariant = GrisFondoTarjeta,
    onSurfaceVariant = TextoSecundario,
    outline = BordeTarjeta,
    background = Blanco,
    onBackground = TextoPrincipal
)

private val DarkColorScheme = darkColorScheme(
    primary = VerdeOscuro,
    onPrimary = Color.White,
    primaryContainer = VerdeClaroFondo,
    onPrimaryContainer = VerdeOscuro,
    secondary = VerdeOscuro,
    onSecondary = Color.White,
    secondaryContainer = GrisFondoTarjeta,
    onSecondaryContainer = TextoPrincipal,
    surface = Blanco,
    onSurface = TextoPrincipal,
    surfaceVariant = GrisFondoTarjeta,
    onSurfaceVariant = TextoSecundario,
    outline = BordeTarjeta,
    background = Blanco,
    onBackground = TextoPrincipal
)

@Composable
fun TECSUPFitTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme // Force clean light design system as specified

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
