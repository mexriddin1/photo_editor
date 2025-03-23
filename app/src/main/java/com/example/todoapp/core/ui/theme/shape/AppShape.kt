package com.example.homewrok16.ui.theme.shape

import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class AppShape(
    val small: Shape,
    val medium: Shape,
    val large: Shape,
)

val appDefaultShape = AppShape(
    small = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
)

internal val LocalShape = staticCompositionLocalOf { appDefaultShape }