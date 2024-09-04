package com.alberto.venture_prueba.home.domain.repository

import com.alberto.venture_prueba.auth.data.remote.EmpleadoResponse

interface EmployeeRepository {
    suspend fun getEmployees(): Result<EmpleadoResponse>
}