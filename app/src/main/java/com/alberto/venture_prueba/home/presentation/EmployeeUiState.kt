package com.alberto.venture_prueba.home.presentation

import com.alberto.venture_prueba.home.data.remote.Empleado

data class EmployeeUiState(
    val isLoading: Boolean = false,
    val nombreCompleto: String = "",
    val foto: String = "",
    val employees: List<Empleado>? = null,
    val error: String? = null
)