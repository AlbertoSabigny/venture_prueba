package com.alberto.venture_prueba.home.domain.usecase

import com.alberto.venture_prueba.home.data.remote.EmpleadoResponse
import com.alberto.venture_prueba.home.domain.repository.EmployeeRepository
import javax.inject.Inject

class GetEmployeesUseCase @Inject constructor(
    private val employeeRepository: EmployeeRepository
) {
    suspend operator fun invoke(): Result<EmpleadoResponse> {
        return employeeRepository.getEmployees()
    }
}