package com.alberto.venture_prueba.auth.data.remote

data class UserResponse(
    val token: String,
    val error: Boolean,
    val codigo: String,
    val acceso: Acceso
)

data class Acceso(
    val codigo: String,
    val ambiente: String,
    val expiration: Boolean,
    val urlAvisoPrivacidad: String,
    val usuarioeTime: UsuarioeTime
)

data class UsuarioeTime(
    val idUsuario: Int,
    val nombreCompleto: String,
    val nombre: String,
    val apellidoPat: String,
    val apellidoMat: String,
    val tipoUsuario: Int,
    val foto: String,
    val codigoTel: String,
    val telefono: Int,
    val aviso: String,
    val cuentaPrueba: Boolean,
    val expiracion: Boolean,
    val fechaVigencia: String,
    val fechaActual: String,
    val costoEmpleado: Double,
    val scia: List<SCia>,
    val szona: List<SZona>
)

data class SCia(
    val cia: Int,
    val nombre: String,
    val idFacturacion: Int,
    val razonSocial: String,
    val maximoEmpleados: Int,
    val fotoLocal: String
)

data class SZona(
    val idZona: Int,
    val descripcion: String,
    val estatus: String
)