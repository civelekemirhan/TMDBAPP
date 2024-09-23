package com.example.tmdbapp.feature.auth.ui.Screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.tmdbapp.AuthColumn
import com.example.tmdbapp.R
import com.example.tmdbapp.common.component.authcomponent.AuthButton
import com.example.tmdbapp.common.component.authcomponent.AuthSwitch
import com.example.tmdbapp.common.component.authcomponent.AuthTextField
import com.example.tmdbapp.feature.auth.data.model.LoginEvent
import com.example.tmdbapp.feature.auth.data.model.LoginViewModel
import com.example.tmdbapp.navigation.NavigationConstant
import com.example.tmdbapp.ui.theme.headerColor
import kotlin.math.log


@Composable
fun LoginScreen(navController: NavController) {


    val loginViewModel:LoginViewModel= hiltViewModel()
    val state by loginViewModel.loginState.collectAsState()


    if (state.loginSuccess) {
        LaunchedEffect(Unit) {
            navController.navigate(NavigationConstant.CONTENT_NAV_GRAPH_ROUTE_KEY) {
                popUpTo(NavigationConstant.LOGIN_SCREEN_ROUTE_KEY) {
                    inclusive = true
                }
            }

        }
    }


        AuthColumn(backImage = R.drawable.movie_app_background) {

            Text(
                text = "GİRİŞ YAP",
                color = headerColor,
                lineHeight = 50.sp,
                fontSize = 50.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(30.dp))

            AuthTextField(
                labelText = "Mail giriniz",
                stateString = state.loginMail,
                onValueChange = {
                    loginViewModel::onEvent.invoke(LoginEvent.SetLoginMail(it))
                })

            Spacer(modifier = Modifier.height(30.dp))

            AuthTextField(
                labelText = "Şifre giriniz",
                stateString = state.loginPassword,
                onValueChange = {
                    loginViewModel::onEvent.invoke(LoginEvent.SetLoginPass(it))
                })

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.wrapContentSize()) {
                    AuthSwitch()
                    Text(
                        text = "Beni hatırla",
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )
                }
                Column(modifier = Modifier.wrapContentSize()) {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = "Şifremi Unuttum",
                            color = Color.White,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(30.dp))

            AuthButton(btnText = "Giriş Yap", 0.6f, 10.dp) {

                loginViewModel::onEvent.invoke(LoginEvent.LoginUser)


            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { navController.navigate(NavigationConstant.REGISTER_SCREEN_ROUTE_KEY) }) {
                    Text(
                        text = "Hesabınız yok mu? Hesap Oluştur",
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif
                    )
                }

            }

        }
    }

