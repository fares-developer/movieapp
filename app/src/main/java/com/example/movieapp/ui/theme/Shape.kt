package com.example.movieapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(Paddings.VeryLow.dp),
    large = RoundedCornerShape(Paddings.Low.dp),
    extraLarge = RoundedCornerShape(Paddings.Medium.dp)
)

enum class Paddings(val dp: Dp){
    Very2Low(dp = 4.dp),
    VeryLow(dp = 8.dp),
    Low(dp = 16.dp),
    Medium(dp = 24.dp),
    High(dp = 36.dp),
    VeryHigh(dp = 48.dp),
}