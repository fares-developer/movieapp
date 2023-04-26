package com.example.movieapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movieapp.R

val readex = FontFamily(
    Font(R.font.readexpro_regular, FontWeight.Normal),
    Font(R.font.readexpro_bold, FontWeight.Bold),
    Font(R.font.readexpro_extralight, FontWeight.ExtraLight),
    Font(R.font.readexpro_light, FontWeight.Light),
    Font(R.font.readexpro_medium, FontWeight.Medium),
    Font(R.font.readexpro_semibold, FontWeight.SemiBold),
)

// Set of Material typography styles to start with
val typography = Typography(
    displayLarge = Typography().displayLarge.copy(fontFamily = readex),
    displayMedium = Typography().displayMedium.copy(fontFamily = readex),
    displaySmall = Typography().displaySmall.copy(fontFamily = readex),
    headlineLarge = Typography().headlineLarge.copy(fontFamily = readex),
    headlineMedium = Typography().headlineMedium.copy(fontFamily = readex),
    headlineSmall = Typography().headlineSmall.copy(fontFamily = readex),
    titleLarge = Typography().titleLarge.copy(fontFamily = readex),
    titleMedium = Typography().titleMedium.copy(fontFamily = readex),
    titleSmall = Typography().titleSmall.copy(fontFamily = readex),
    bodyLarge = Typography().bodyLarge.copy(fontFamily = readex),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = readex),
    bodySmall = Typography().bodySmall.copy(fontFamily = readex),
    labelLarge = Typography().labelLarge.copy(fontFamily = readex),
    labelMedium = Typography().labelMedium.copy(fontFamily = readex),
    labelSmall = Typography().labelSmall.copy(fontFamily = readex),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
