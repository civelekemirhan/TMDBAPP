package com.example.tmdbapp.feature.auth.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.map {
        it.copy(
            loginMail = _loginState.value.loginMail,
            loginPassword = _loginState.value.loginPassword
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LoginState())




    fun onEvent(event: LoginEvent) {

        when (event) {
            is LoginEvent.LoginUser -> {
                if (
                    !_loginState.value.loginMail.equals("") ||
                    !_loginState.value.loginPassword.equals("")
                ) {
                    val user = User(
                        mail = loginState.value.loginMail,
                        pass = loginState.value.loginPassword
                    )
                   viewModelScope.launch {

                            if (authRepository.login(user.mail,user.pass).isSuccess) {
                                _loginState.update {
                                    it.copy(
                                        loginMail = "",
                                        loginPassword = "",
                                        loginSuccess = true,
                                        loginMessage = "Giriş Başarılı"
                                    )
                                }


                            } else {
                                _loginState.update {
                                    it.copy(
                                        loginPassword = "",
                                        loginSuccess = false,
                                        loginMessage = "Giriş Başarısız"
                                    )
                                }

                            }

                    }
                } else {
                    _loginState.update {
                        it.copy(
                            loginMessage = "Boş alan bırakmayınız"
                        )
                    }
                }


            }

            is LoginEvent.SetLoginMail -> {
                _loginState.update {
                    it.copy(loginMail = event.mail)
                }
            }

            is LoginEvent.SetLoginPass -> {
                _loginState.update {
                    it.copy(loginPassword = event.pass)
                }
            }
        }

    }



}