package com.example.movieapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.core.SocialMedia
import com.example.movieapp.ui.theme.Paddings
import com.example.movieapp.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    vm: AuthViewModel = viewModel(factory = AuthViewModel.factory),
    navController: NavController,
    destiny: MovieScreens,
    onclickToSigUp: () -> Unit = {}
) {

    val loginUIState by vm.loginState.collectAsState()

    val eventNav = {
        vm.loginWithMail()
        if (!loginUIState.errorMail && !loginUIState.errorPassword) {
            vm.navigateToHome(navController, destiny.name)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
    ) {
        Text(
            text = stringResource(id = R.string.login),
            style = MaterialTheme.typography.displayLarge
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(Paddings.Low.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(
                top = 32.dp,
                start = Paddings.VeryHigh.dp,
                end = Paddings.VeryHigh.dp
            )
        ) {
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = loginUIState.mail,
                onValueChange = { vm.updateMail(it, true) },
                label = { Text(stringResource(id = R.string.label_mail)) },
                maxLines = 1,
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        vm.showPassword(
                            showpass = true, screen = true
                        )
                    }) {
                        Icon(
                            imageVector = loginUIState.iconEmail,
                            contentDescription = stringResource(id = R.string.visibility_icon)
                        )
                    }
                },
                isError = loginUIState.errorMail
            )
            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = loginUIState.password,
                onValueChange = { vm.updatePassword(it, true) },
                trailingIcon = {
                    IconButton(onClick = {
                        vm.showPassword(
                            loginUIState.showPassword,
                            true
                        )
                    }) {
                        Icon(
                            imageVector = loginUIState.iconVisibility,
                            contentDescription = stringResource(id = R.string.visibility_icon)
                        )
                    }
                },
                label = { Text(text = stringResource(id = R.string.label_password)) },
                visualTransformation =
                if (loginUIState.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                maxLines = 1,
                singleLine = true,
                isError = loginUIState.errorPassword
            )
            Button(
                onClick = eventNav,
                modifier = modifier
                    .padding(top = Paddings.Medium.dp)
                    .fillMaxWidth()
                    .height(Paddings.VeryHigh.dp)
            ) {
                Text(
                    text = stringResource(id = loginUIState.buttonLabel),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(horizontal = 40.dp)
        ) {
            Text(
                modifier = modifier
                    .clickable { vm.recoveryPass() },
                text = stringResource(id = R.string.forgot_pass),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                )
            )
            TextButton(onClick = onclickToSigUp) {
                Text(
                    text = stringResource(id = R.string.signup),
                    textAlign = TextAlign.Center
                )
            }
        }
        Column(
            modifier = modifier.padding(top = Paddings.Low.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Paddings.Low.dp)
        ) {
            Text(
                modifier = modifier.padding(vertical = Paddings.Low.dp),
                text = stringResource(id = R.string.use_social_account),
                style = MaterialTheme.typography.bodySmall
            )
            //TODO: Icons Social Media
            SocialMedia(modifier, vm)
        }
    }
}

/*

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoginPreview() {
    MovieAppTheme {
        LoginScreen()
    }
}*/
