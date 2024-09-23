package com.example.tmdbapp.feature.auth.data.model

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.tmdbapp.OnBoardingPages
import com.example.tmdbapp.navigation.NavigationConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {


    private val _registerState = MutableStateFlow(RegisterState())
    val state = _registerState.map { state ->
        state.copy(
            registerMail = _registerState.value.registerMail,
            registerUserName = _registerState.value.registerUserName,
            registerPassword = _registerState.value.registerPassword,
            registerSuccess = _registerState.value.registerSuccess,
            registerLoading = _registerState.value.registerLoading,
            registerRePassword = _registerState.value.registerRePassword
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RegisterState())


    fun onEvent(event: RegisterEvent) {

        when (event) {
             RegisterEvent.RegisterUser -> {
                if(!state.value.registerMail.equals("")||!state.value.registerUserName.equals("")||!state.value.registerPassword.equals("")){
                    if (state.value.registerPassword.equals(state.value.registerRePassword)) {
                        val user = User(
                            mail = state.value.registerMail,
                            username = state.value.registerUserName,
                            pass = state.value.registerPassword
                        )
                        viewModelScope.launch {
                                if (authRepository.register(user.username, user.mail, user.pass).isSuccess){
                                    _registerState.update {
                                        it.copy(
                                            registerMail = "",
                                            registerUserName = "",
                                            registerPassword = "",
                                            registerRePassword = "",
                                            registerSuccess = true,
                                            registerMessage = "Kayıt Başarılı"
                                        )
                                    }
                                }
                                else{
                                    _registerState.update {
                                        it.copy(
                                            registerPassword = "",
                                            registerRePassword = "",
                                            registerSuccess = false,
                                            registerMessage = "Kayıt Başarılı Değil"
                                        )
                                    }
                                }

                        }
                    } else {
                        _registerState.update {
                            it.copy(
                                registerPassword = "",
                                registerRePassword = "",
                                registerMessage = "Şifreler Eşleşmiyor"
                            )
                        }
                    }
                }else{
                    _registerState.update {
                        it.copy(
                            registerMessage = "Boş alan bırakmayınız"
                        )
                    }
                }

            }

            is RegisterEvent.SetRegisterMail -> {
                _registerState.update {
                    it.copy(
                        registerMail = event.mail
                    )
                }

            }

            is RegisterEvent.SetRegisterPass -> {
                _registerState.update {
                    it.copy(
                        registerPassword = event.pass
                    )
                }
            }

            is RegisterEvent.SetRegisterUserName -> {
                _registerState.update {
                    it.copy(
                        registerUserName = event.userName
                    )
                }
            }

            is RegisterEvent.SetRegisterRePass -> {
                _registerState.update {
                    it.copy(
                        registerRePassword = event.rePass
                    )
                }
            }
        }

    }



}