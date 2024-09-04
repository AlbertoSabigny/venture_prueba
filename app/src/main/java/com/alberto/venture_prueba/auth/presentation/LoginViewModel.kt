package com.alberto.venture_prueba.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.venture_prueba.auth.data.repository.LoginException
import com.alberto.venture_prueba.auth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> updateEmail(event.email)
            is LoginEvent.PasswordChanged -> updatePassword(event.password)
            is LoginEvent.LoginClicked -> performLogin()
            is LoginEvent.ErrorDismissed -> dismissError()
        }
    }

    private fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    private fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    private fun performLogin() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            val email = _uiState.value.email
            val password = _uiState.value.password

            val result = loginUseCase(email, password)

            result.fold(
                onSuccess = { userResponse ->
                    _uiState.update { it.copy(isLoggedIn = true, isLoading = false) }
                },
                onFailure = { exception ->
                    when (exception) {
                        is LoginException -> handleLoginException(exception)
                        else -> _uiState.update { it.copy(error = exception.message, isLoading = false) }
                    }
                }
            )
        }
    }

    private fun handleLoginException(exception: LoginException) {
        val errorMessage = when (exception.errorCode) {
            "ET000" -> {
                _uiState.update { it.copy(isLoggedIn = true, isLoading = false) }
                return
            }
            "ET201" -> "El campo 'correo electrónico' es obligatorio."
            "ET202" -> "El campo 'contraseña' es obligatorio."
            "ET217" -> "Password invalido."
            "ET216" -> "El usuario no existe."
            else -> "Ha ocurrido un error. Por favor, inténtalo de nuevo más tarde."
        }
        _uiState.update { it.copy(error = errorMessage, isLoading = false) }
    }
    private fun dismissError() {
        _uiState.update { it.copy(error = null) }
    }
}
