package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.R
import com.example.movieapp.ui.AuthViewModel
import com.example.movieapp.ui.theme.Cabin
import com.example.movieapp.ui.theme.MovieAppTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onclickRegister: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {

    val regUIState by viewModel.regState.collectAsState()
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(horizontal = 40.dp, vertical = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.signup),
                style = MaterialTheme.typography.displayLarge,
                fontFamily = Cabin
            )
            OutlinedTextField(
                modifier = modifier,
                value = regUIState.mail,
                onValueChange = { viewModel.updateMail(it, false) },
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
                onValueChange = { viewModel.updatePhone(it) },
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
                onValueChange = { viewModel.updatePassword(it, false) },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.showPassword(
                            regUIState.showPassword,
                            false
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = regUIState.iconVisibility),
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
                onValueChange = { viewModel.updateConfirmPassword(it) },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.showPassword(
                            regUIState.showPassword,
                            false
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = regUIState.iconVisibility),
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
            onClick = onclickRegister,
            modifier = modifier.fillMaxWidth().padding(horizontal = 40.dp)
        ) {
            Text(text = stringResource(id = regUIState.buttonLabel))
        }
        Text(
            modifier = modifier.padding(16.dp),
            text = stringResource(id = R.string.use_social_account_tologin),
            style = MaterialTheme.typography.bodySmall
        )
        SocialMedia()
    }
}

@Composable
@Preview(showBackground = true)
fun RegisterPreview() {
    MovieAppTheme {
        RegisterScreen(onclickRegister = {})
    }
}