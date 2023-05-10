package com.example.movieapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.lifecycle.ViewModel
import com.example.movieapp.R
import com.example.movieapp.ui.state.LoginState
import com.example.movieapp.ui.state.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    private var _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private var _regState = MutableStateFlow(RegisterState())
    val regState = _regState.asStateFlow()

    fun login(click: () -> Unit): () -> Unit {
        //TODO: Implement Firebase Login
        return click
    }

    fun register(click: () -> Unit): () -> Unit {
        //TODO: Implement Firebase Register
        return click
    }

    /**
     * @param input del email
     * @param screen state de referencia, true para login y false para register
     */
    fun updateMail(mail: String, screen: Boolean) {
        if (screen) {
            _loginState.value = loginState.value.copy(mail = mail)
        } else _regState.value = regState.value.copy(mail = mail)
    }

    fun updatePhone(phone: String) {
        _regState.value = regState.value.copy(phone = phone)
    }

    fun updatePassword(password: String, screen: Boolean) {
        if (screen) {
            _loginState.value = loginState.value.copy(password = password)
        } else _regState.value = regState.value.copy(password = password)
    }

    fun updateConfirmPassword(password: String) {
        _regState.value = regState.value.copy(confirmPassword = password)
    }


    fun showPassword(showpass: Boolean, screen: Boolean) {

        if (screen) {
            _loginState.value = loginState.value.copy(
                showPassword = !showpass,
                iconVisibility = if (showpass) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility
            )
        } else _regState.value = regState.value.copy(
            showPassword = !showpass,
            iconVisibility = if (showpass) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility
        )
    }

    fun recoveryPass() {
        // TODO : Implement Recovery Passowrd
    }
}