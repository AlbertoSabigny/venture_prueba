package com.alberto.venture_prueba.auth.presentation

sealed interface LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    object LoginClicked : LoginEvent
    object ErrorDismissed : LoginEvent
}