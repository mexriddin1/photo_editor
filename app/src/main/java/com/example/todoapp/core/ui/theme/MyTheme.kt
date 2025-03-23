package com.example.todoapp.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.example.todoapp.core.ui.theme.color.ColorScheme
import com.example.todoapp.core.ui.theme.color.LocalColorScheme
import com.example.homewrok16.ui.theme.shape.AppShape
import com.example.homewrok16.ui.theme.shape.LocalShape
import com.example.homewrok16.ui.theme.type.LocalTypography
import com.example.homewrok16.ui.theme.type.Typography

object MyTheme {
    val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColorScheme.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
    val shape: AppShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current
    val isDark: Boolean
        @Composable
        @ReadOnlyComposable
        get() = isSystemInDarkTheme()
}

@Composable
fun AppMyTheme(
    colorScheme: ColorScheme = MyTheme.colorScheme,
    typography: Typography = MyTheme.typography,
    shape: AppShape = MyTheme.shape,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColorScheme provides colorScheme,
        LocalTypography provides typography,
        LocalShape provides shape
    ) {
        ProvideTextStyle(value = typography.body1, content = content)
    }
}
