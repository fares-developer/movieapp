package com.example.movieapp.ui.state

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.movieapp.R

data class LoginState(
    @StringRes var buttonLabel: Int = R.string.login,
    var mail: String = "",
    var password: String = "",
    var showPassword: Boolean = false,
    var iconVisibility: ImageVector = Icons.Rounded.VisibilityOff,
    val iconEmail: ImageVector = Icons.Rounded.Email
)

data class RegisterState(
    @StringRes var buttonLabel: Int = R.string.create_acount,
    var mail: String = "",
    var password: String = "",
    var phone: String = "",
    var confirmPassword: String = "",
    var showPassword: Boolean = false,
    var iconVisibility: ImageVector = Icons.Rounded.VisibilityOff,
    val iconPhone: ImageVector = Icons.Rounded.Phone,
    val iconEmail: ImageVector = Icons.Rounded.Email
)