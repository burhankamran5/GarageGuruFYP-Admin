package com.bkcoding.garagegurufyp_admin.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bkcoding.garagegurufyp_admin.R
import com.bkcoding.garagegurufyp_admin.navigation.Screen
import com.bkcoding.garagegurufyp_admin.ui.component.GarageButton
import com.bkcoding.garagegurufyp_admin.ui.component.NameTextFields
import com.bkcoding.garagegurufyp_admin.ui.component.PasswordTextFields
import com.bkcoding.garagegurufyp_admin.ui.theme.GarageGuruFYPAdminTheme
import com.bkcoding.garagegurufyp_admin.util.isValidEmail
import com.bkcoding.garagegurufyp_admin.util.showToast

@Composable
fun LoginScreen(navController: NavController) {
    LoginScreen(onSignInClick = { navController.navigate(Screen.HomeScreen.route) })
}

@Composable
private fun LoginScreen(onSignInClick: () -> Unit) {
    val context = LocalContext.current
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome_back),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.login_to_continue),
            fontSize = 20.sp,
            color = colorResource(id = R.color.spanish_gray)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(id = R.string.email),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        NameTextFields(
            placeholder = stringResource(id = R.string.enter_you_email),
            textValue = email,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = ""
                )
            }) {
            email = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.password),
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        PasswordTextFields(
            placeholder = stringResource(id = R.string.enter_password),
            password = password,
            passwordVisibility = passwordVisibility,
            onTextChange = { password = it },
            onToggleClick = { passwordVisibility = it }
        )
        Spacer(modifier = Modifier.height(30.dp))
        GarageButton(
            modifier = Modifier
                .widthIn(min = 300.dp, max = 600.dp)
                .padding(top = 30.dp),
            buttonText = stringResource(id = R.string.sign_in)
        ) {
            if (email.isEmpty() || password.isEmpty()) {
                showToast(context = context, context.getString(R.string.empty_fields_error))
            } else if (!isValidEmail(email)) {
                showToast(context, context.getString(R.string.email_valid_error))
            } else {
                onSignInClick()
                //onLoginClick(UserCredential(email = email, password = passwords))
            }
            onSignInClick()
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    GarageGuruFYPAdminTheme {
        LoginScreen(onSignInClick = {})
    }
}