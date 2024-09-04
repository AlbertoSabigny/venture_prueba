package com.alberto.venture_prueba.home.data.remote

data class EmpleadoRequest(
    val filtroEmpleado: String,
    val idCia: Int,
    val idUsuario: Int,
    val numRegistros: Int,
    val pagina: Int
)

data class EmpleadoResponse(
    val codigo: String,
    val totalRegistros: Int,
    val empleado: List<Empleado>
)

data class Empleado(
    val idCia: Int,
    val idEmpleado: Int,
    val nombre: String,
    val apellidoPat: String,
    val apellidoMat: String,
    val fechaIngreso: String,
    val estatus: String,
    val aviso: Boolean
)