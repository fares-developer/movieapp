package com.example.movieapp.ui.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movieapp.MainActivity
import com.example.movieapp.MovieApp
import com.example.movieapp.data.repository.local.UserRepo
import com.example.movieapp.data.repository.local.UserRepoImpl
import com.example.movieapp.data.repository.local.entities.UserEntity
import com.example.movieapp.ui.state.AuthState
import com.example.movieapp.ui.state.LoginState
import com.example.movieapp.ui.state.RegisterState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val userRepo: UserRepoImpl
) : ViewModel() {

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieApp)
                val userRepo = app.container.userRepo
                AuthViewModel(userRepo = userRepo)
            }
        }
    }

    private var _authState = MutableStateFlow(AuthState())
    val authState = _authState.asStateFlow()

    private var _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private var _regState = MutableStateFlow(RegisterState())
    val regState = _regState.asStateFlow()

    private var auth: FirebaseAuth = Firebase.auth

    init {
        // Check if user is signed in (non-null) and update UI accordingly.
        if (_authState.value.user != null) {
            reload()
        }
    }

    private fun reload() {
        _authState.value.navigateToHome = true
    }

    fun login() {
        //TODO: Implement Firebase Login
        viewModelScope.launch{
            val logged = auth.signInWithEmailAndPassword(
                _loginState.value.mail,
                _loginState.value.password
            ).isSuccessful

            if(logged){
                _authState.value =
                    _authState.value.copy(user = auth.currentUser, navigateToHome = true)
                _authState.value.user = auth.currentUser
            }else {
                _loginState.value.errorMail = true
                _loginState.value.errorPassword = true
            }
        }
    }

    fun register(){
        //TODO: Implement Firebase Register
        viewModelScope.launch {
            val create = auth.createUserWithEmailAndPassword(
                _regState.value.mail,
                _regState.value.confirmPassword
            ).isSuccessful

            if (create){
                _authState.value.user = auth.currentUser
                _authState.value.navigateToHome = true
            } else {
                _regState.value.errorMail = true
                _regState.value.errorPassword = true
                _regState.value.errorCPassword = true
            }
        }
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