package com.example.homewrok16.ui.theme.type

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.example.todoapp.R


val typography = Typography(
    title1 = TextStyle(
        fontSize = 26.sp,
        fontWeight = W600,
        textAlign = TextAlign.Left,
        lineHeight = 28.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium))
    ),
    title2 = TextStyle(
        fontSize = 22.sp,
        fontWeight = W600,
        textAlign = TextAlign.Left,
        lineHeight = 28.sp,
        letterSpacing = TextUnit(0.04f, TextUnitType.Sp),
        fontFamily = FontFamily(Font(R.font.futuru_semibold))
    ),
    title3 = TextStyle(
        fontSize = 18.sp,
        fontWeight = W600,
        textAlign = TextAlign.Left,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.futuru_bold))
    ),
    body1 = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Left,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.futuru_medium))
    ),
    body2 = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.W400,
        textAlign = TextAlign.Left,
        lineHeight = 24.sp,
        fontFamily = FontFamily(Font(R.font.futuru_regular))
    ),
    body3 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Left,
        lineHeight = 21.sp,
        fontFamily = FontFamily(Font(R.font.poppins_regular))
    ),
    caption1 = TextStyle(
        fontSize = 14.sp,
        fontWeight = W600,
        textAlign = TextAlign.Left,
        lineHeight = 18.sp,
        fontFamily = FontFamily(Font(R.font.futuru_bold))
    ),
    caption2 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Left,
        lineHeight = 18.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium))
    )
)

data class Typography(
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val caption1: TextStyle,
    val caption2: TextStyle,
)

internal val LocalTypography = staticCompositionLocalOf { typography }