package com.alberto.venture_prueba.home.presentation


import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alberto.venture_prueba.auth.data.remote.Empleado
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

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    init {
        Log.d(TAG, "ViewModel initialized")
        loadUserData()
        loadEmployees()
    }

    private fun loadUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Loading user data")
            val nombreCompleto = sharedPreferences.getString("nombreCompleto", "") ?: ""
            val foto = sharedPreferences.getString("foto", "") ?: ""
            _uiState.update { it.copy(nombreCompleto = nombreCompleto, foto = foto) }
            Log.d(TAG, "User data loaded: $nombreCompleto, Foto: $foto")
        }
    }


    fun loadEmployees() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d(TAG, "Loading employees")
            _uiState.update { it.copy(isLoading = true) }
            try {
                val result = getEmployeesUseCase()
                val employees = result.getOrNull()?.empleado ?: emptyList()
                Log.d(TAG, "Employees loaded. Count: ${employees.size}")
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        employees = employees,
                        error = null
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error loading employees", e)
                _uiState.update {
                    it.copy(isLoading = false, error = e.message, employees = emptyList())
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel cleared")
    }

    companion object {
        private const val TAG = "EmployeeViewModel"
    }
}


data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val nombreCompleto: String = "",
    val foto: String = "",
    val employees: List<Empleado>? = null,
    val error: String? = null
)