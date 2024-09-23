package com.example.tmdbapp.feature.auth.ui.Screen


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tmdbapp.AuthColumn
import com.example.tmdbapp.R
import com.example.tmdbapp.common.component.authcomponent.AuthButton
import com.example.tmdbapp.common.component.authcomponent.AuthTextField
import com.example.tmdbapp.feature.auth.data.model.RegisterEvent
import com.example.tmdbapp.feature.auth.data.model.RegisterViewModel
import com.example.tmdbapp.navigation.NavigationConstant
import com.example.tmdbapp.ui.theme.headerColor
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@Composable
fun RegisterScreen(navController: NavController) {

    val registerViewModel: RegisterViewModel = hiltViewModel()
    val state by registerViewModel.state.collectAsState()


        if (state.registerSuccess) {
            LaunchedEffect(Unit) {
                navController.navigate(NavigationConstant.LOGIN_SCREEN_ROUTE_KEY) {
                    popUpTo(NavigationConstant.REGISTER_SCREEN_ROUTE_KEY) {
                        inclusive = true
                    }
                }

            }
        }





    AuthColumn(backImage = R.drawable.movie_app_background) {


        Text(
            text = "KAYIT OL",
            color = headerColor,
            lineHeight = 50.sp,
            fontSize = 50.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(30.dp))

        AuthTextField(
            labelText = "Mail giriniz",
            stateString = state.registerMail,
            onValueChange = {
                registerViewModel::onEvent.invoke(RegisterEvent.SetRegisterMail(it))
            })

        Spacer(modifier = Modifier.height(30.dp))

        AuthTextField(
            labelText = "Kullanıcı Adı giriniz",
            stateString = state.registerUserName,
            onValueChange = { text ->
                registerViewModel::onEvent.invoke(RegisterEvent.SetRegisterUserName(text))
            })

        Spacer(modifier = Modifier.height(30.dp))

        AuthTextField(
            labelText = "Şifre giriniz",
            stateString = state.registerPassword,
            onValueChange = {
                registerViewModel::onEvent.invoke(RegisterEvent.SetRegisterPass(it))
            })

        Spacer(modifier = Modifier.height(30.dp))

        AuthTextField(
            labelText = "Şifre tekrar giriniz",
            stateString = state.registerRePassword,
            onValueChange = {
                registerViewModel::onEvent.invoke(RegisterEvent.SetRegisterRePass(it))
            })

        Spacer(modifier = Modifier.height(30.dp))

        AuthButton(btnText = "Kayıt Ol", 0.6f, 10.dp) {

                registerViewModel::onEvent.invoke(RegisterEvent.RegisterUser)

        }
    }
}





