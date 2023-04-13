package com.example.movieapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.R
import com.example.movieapp.data.DataSource
import com.example.movieapp.ui.AuthViewModel
import com.example.movieapp.ui.theme.Cabin
import com.example.movieapp.ui.theme.MovieAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = viewModel(),
    onclickLoginButton: () -> Unit = {},
    onclickToSigUp: () -> Unit = {}
) {

    val loginUIState by viewModel.loginState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.displayLarge,
            fontFamily = Cabin
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(vertical = 16.dp, horizontal = 40.dp)
        ) {
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = loginUIState.mail,
                onValueChange = { viewModel.updateMail(it, true) },
                label = { Text(stringResource(id = R.string.label_mail)) },
                maxLines = 1,
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = loginUIState.icon),
                            contentDescription = stringResource(id = R.string.visibility_icon)
                        )
                    }
                },
            )
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = loginUIState.password,
                onValueChange = { viewModel.updatePassword(it, true) },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.showPassword(
                            loginUIState.showPassword,
                            true
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = loginUIState.icon),
                            contentDescription = stringResource(id = R.string.visibility_icon)
                        )
                    }
                },
                label = { Text(text = stringResource(id = R.string.label_password)) },
                visualTransformation =
                if (loginUIState.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                maxLines = 1,
                singleLine = true
            )
            Button(
                onClick = onclickLoginButton,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = loginUIState.buttonLabel))
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(horizontal = 40.dp)
        ) {
            Text(
                modifier = modifier
                    .padding(vertical = 12.dp)
                    .clickable { viewModel.recoveryPass() },
                text = stringResource(id = R.string.forgot_pass),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                )
            )
            TextButton(onClick = onclickToSigUp) {
                Text(text = stringResource(id = R.string.signup))
            }
        }
        Text(
            modifier = modifier.padding(vertical = 16.dp),
            text = stringResource(id = R.string.use_social_account),
            style = MaterialTheme.typography.bodySmall
        )
        //TODO: Icons Social Media
        SocialMedia(modifier)
    }
}


@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    MovieAppTheme {
        LoginScreen(onclickLoginButton = {})
    }
}