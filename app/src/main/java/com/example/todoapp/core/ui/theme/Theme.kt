package com.example.homewrok16.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.todoapp.core.ui.theme.AppMyTheme
import com.example.todoapp.core.ui.theme.MyTheme.typography
import com.example.todoapp.core.ui.theme.color.darkColorScheme
import com.example.todoapp.core.ui.theme.color.lightColorScheme

@Composable
fun MyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary1.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }

    }

    AppMyTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}