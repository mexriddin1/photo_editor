package com.example.todoapp.core.ui.theme.color

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Primary1 = Color(0xFF9EE22F)
val Primary2 = Color(0xFF9CF50D)
val Primary3 = Color(0xFFD4FF6E)
val Primary4 = Color(0xFFEFFFB7)

val Neutral1 = Color(0xFF101010)
val Neutral2 = Color(0xFF161616)
val Neutral3 = Color(0xFF989898)
val Neutral4 = Color(0xFFCACACA)
val Neutral5 = Color(0xFFE0E0E0)
val Neutral6 = Color(0xFFFFFFFF)
val Neutral7 = Color(0xFF202020)

val Semantic1 = Color(0xFFFF4267)
val Semantic2 = Color(0xFFFFAF2A)
val Semantic3 = Color(0xFF52D5BA)
val Semantic4 = Color(0xFFFB6B18)


val lightColorScheme = ColorScheme(
    primary1 = Primary1,
    primary2 = Primary2,
    primary3 = Primary3,
    primary4 = Primary4,

    neutral1 = Neutral1,
    neutral2 = Neutral2,
    neutral3 = Neutral3,
    neutral4 = Neutral4,
    neutral5 = Neutral5,
    neutral6 = Neutral6,
    neutral7 =Neutral7,

    semantic1 = Semantic1,
    semantic2 = Semantic2,
    semantic3 = Semantic3,
    semantic4 = Semantic4,
)

val darkColorScheme = ColorScheme(
    primary1 = Primary1,
    primary2 = Primary2,
    primary3 = Primary3,
    primary4 = Primary4,

    neutral1 = Neutral1,
    neutral2 = Neutral2,
    neutral3 = Neutral3,
    neutral4 = Neutral4,
    neutral5 = Neutral5,
    neutral6 = Neutral6,
    neutral7 =Neutral7,

    semantic1 = Semantic1,
    semantic2 = Semantic2,
    semantic3 = Semantic3,
    semantic4 = Semantic4,
)

internal val LocalColorScheme = staticCompositionLocalOf { lightColorScheme }
