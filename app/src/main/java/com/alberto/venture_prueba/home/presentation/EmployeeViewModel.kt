package com.alberto.venture_prueba.home.presentation


import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.venture_prueba.home.domain.usecase.GetEmployeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val getEmployeesUseCase: GetEmployeesUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(EmployeeUiState())
    val uiState: StateFlow<EmployeeUiState> = _uiState.asStateFlow()

    init {
        loadUserData()
        loadEmployees()
    }

    private fun loadUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val nombreCompleto = sharedPreferences.getString("nombreCompleto", "").orEmpty()
            val foto = sharedPreferences.getString("foto", "").orEmpty()
            _uiState.update { it.copy(nombreCompleto = nombreCompleto, foto = foto) }
        }
    }

    fun loadEmployees() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val result = getEmployeesUseCase()
                val employees = result.getOrNull()?.empleado.orEmpty()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        employees = employees,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message, employees = emptyList())
                }
            }
        }
    }
}


