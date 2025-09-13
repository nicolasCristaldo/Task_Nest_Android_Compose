package com.nicolascristaldo.tasknest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nicolascristaldo.tasknest.R

val VendSans = FontFamily(
    Font(R.font.vend_sans_regular, FontWeight.Normal),
    Font(R.font.vend_sans_bold, FontWeight.Bold)
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = VendSans,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.5.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = VendSans,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = VendSans,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),

    titleLarge = TextStyle(
        fontFamily = VendSans,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),

    titleMedium = TextStyle(
        fontFamily = VendSans,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = VendSans,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.3.sp
    ),

    labelLarge = TextStyle(
        fontFamily = VendSans,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)