package com.example.movieapp.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.example.movieapp.MainActivity
import com.example.movieapp.MovieApp
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

    var auth: FirebaseAuth = Firebase.auth
        private set

    init {
        // Check if user is signed in (non-null) and update UI accordingly.
        _authState.value.user = auth.currentUser
        if (_authState.value.user != null) {
            _authState.value.logged = true
        }
    }

    private suspend fun addUser() {
        _authState.value.user = auth.currentUser
        _authState.value.logged = true
        userRepo.insert(
            UserEntity(email = _regState.value.mail, password = _regState.value.password)
        )
    }

    fun navigateToHome(nav: NavController, destiny: String) {
        nav.popBackStack()
        nav.navigate(destiny)
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

    fun loginWithMail() {
        //TODO: Implement Firebase Login
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(
                _loginState.value.mail.trim(),
                _loginState.value.password
            ).addOnCompleteListener(MainActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    viewModelScope.launch { addUser() }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        MainActivity().baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    viewModelScope.launch {
                        _loginState.value.errorMail = true
                        _loginState.value.errorPassword = true
                    }
                }
            }
        }
    }

    fun registerWithMail() {
        //TODO: Implement Firebase Register
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(
                _regState.value.mail.trim(),
                _regState.value.password
            ).addOnCompleteListener(MainActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    viewModelScope.launch { addUser() }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        MainActivity().baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    viewModelScope.launch {
                        _regState.value.errorMail = true
                        _regState.value.errorPassword = true
                        _regState.value.errorCPassword = true
                    }
                }
            }
        }

    }

    fun loginWithGoogle() {

    }

    fun loginWithMeta() {
        TODO("Not yet implemented")
    }

    fun loginWithTwitter() {
        TODO("Not yet implemented")
    }

    fun recoveryPass() {
        TODO("Not yet implemented")
    }
}