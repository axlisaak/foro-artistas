package com.example.act_clase_pf.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data_core.model.User
import com.example.data_core.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val user: User) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository()


    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState = _uiState.asStateFlow()

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val displayName = MutableStateFlow("")
    val selectedRole = MutableStateFlow("USER") // Por defecto, el usuario es "USER"



    fun onLoginClicked() {
        if (email.value.isBlank() || password.value.isBlank()) {
            _uiState.value = LoginUiState.Error("Correo y contraseña no pueden estar vacíos.")
            return
        }
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            val result = authRepository.login(email.value, password.value)
            result.onSuccess { user ->
                _uiState.value = LoginUiState.Success(user)
            }.onFailure { exception ->
                _uiState.value = LoginUiState.Error(mapAuthExceptionToSpanish(exception))
            }
        }
    }

    fun onRegisterClicked() {
        if (email.value.isBlank() || password.value.isBlank() || displayName.value.isBlank()) {
            _uiState.value = LoginUiState.Error("Todos los campos son requeridos para el registro.")
            return
        }
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            val result = authRepository.register(
                email = email.value,
                pass = password.value,
                name = displayName.value,
                role = selectedRole.value
            )
            result.onSuccess {
                onLoginClicked()
            }.onFailure { exception ->
                _uiState.value = LoginUiState.Error(mapAuthExceptionToSpanish(exception)) //
            }
        }
    }

    fun onGoogleSignIn(idToken: String, displayName: String, email: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            val result = authRepository.loginWithGoogle(idToken, displayName, email)
            result.onSuccess { user ->
                _uiState.value = LoginUiState.Success(user)
            }.onFailure {
                _uiState.value = LoginUiState.Error(it.message ?: "Error con Google Sign-In")
            }
        }
    }

    fun resetState() {
        _uiState.update { LoginUiState.Idle }
    }
    private fun mapAuthExceptionToSpanish(e: Throwable): String {
        return when (e) {
            is FirebaseAuthInvalidCredentialsException -> "La contraseña o el correo son incorrectos."
            is FirebaseAuthUserCollisionException -> "Este correo electrónico ya está en uso."
            is FirebaseAuthWeakPasswordException -> "La contraseña es muy débil. Debe tener al menos 6 caracteres."
            else -> e.message ?: "Ocurrió un error desconocido."
        }
    }
}