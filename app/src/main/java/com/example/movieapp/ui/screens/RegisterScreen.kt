package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.ui.AuthViewModel
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.ui.theme.Paddings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController:NavController,
    destiny: MovieScreens,
    vm: AuthViewModel = viewModel()
) {

    val regUIState by vm.regState.collectAsState()
    val eventNav = {
        vm.register()
        navController.popBackStack()
        navController.navigate(destiny.name)
    }


    MovieAppTheme {
        Column(
            modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Paddings.VeryLow.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(
                    horizontal = Paddings.VeryHigh.dp,
                    vertical = Paddings.Low.dp
                )
            ) {
                Text(
                    text = stringResource(id = R.string.signup),
                    style = MaterialTheme.typography.displayLarge
                )
                OutlinedTextField(
                    modifier = modifier,
                    value = regUIState.mail,
                    onValueChange = { vm.updateMail(it, false) },
                    label = { Text(stringResource(id = R.string.label_mail)) },
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            imageVector = regUIState.iconEmail,
                            contentDescription = stringResource(id = R.string.email)
                        )
                    }
                )
                OutlinedTextField(
                    modifier = modifier,
                    value = regUIState.phone,
                    onValueChange = { vm.updatePhone(it) },
                    trailingIcon = {
                        Icon(
                            imageVector = regUIState.iconPhone,
                            contentDescription = stringResource(id = R.string.phone_label)
                        )
                    },
                    label = { Text(stringResource(id = R.string.phone_label)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    maxLines = 1,
                    singleLine = true
                )
                OutlinedTextField(
                    modifier = modifier,
                    value = regUIState.password,
                    onValueChange = { vm.updatePassword(it, false) },
                    trailingIcon = {
                        IconButton(onClick = {
                            vm.showPassword(
                                regUIState.showPassword,
                                false
                            )
                        }) {
                            Icon(
                                imageVector = regUIState.iconVisibility,
                                contentDescription = stringResource(id = R.string.visibility_icon)
                            )
                        }
                    },
                    label = { Text(stringResource(id = R.string.label_password)) },
                    visualTransformation =
                    if (regUIState.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    maxLines = 1,
                    singleLine = true
                )
                OutlinedTextField(
                    modifier = modifier,
                    value = regUIState.confirmPassword,
                    onValueChange = { vm.updateConfirmPassword(it) },
                    trailingIcon = {
                        IconButton(onClick = {
                            vm.showPassword(
                                regUIState.showPassword,
                                false
                            )
                        }) {
                            Icon(
                                imageVector = regUIState.iconVisibility,
                                contentDescription = stringResource(id = R.string.visibility_icon)
                            )
                        }
                    },
                    label = { Text(stringResource(id = R.string.confirm_pass)) },
                    visualTransformation =
                    if (regUIState.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    maxLines = 1,
                    singleLine = true
                )
            }
            Button(
                onClick = eventNav,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = Paddings.VeryHigh.dp)
            ) {
                Text(
                    text = stringResource(id = regUIState.buttonLabel),
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Text(
                modifier = modifier.padding(Paddings.Low.dp),
                text = stringResource(id = R.string.use_social_account_tologin),
                style = MaterialTheme.typography.bodySmall
            )
            SocialMedia()
        }
    }
}

/*
@Composable
@Preview(showBackground = true)
fun RegisterPreview() {
    MovieAppTheme {
        RegisterScreen(onclickRegister = {})
    }
}*/
