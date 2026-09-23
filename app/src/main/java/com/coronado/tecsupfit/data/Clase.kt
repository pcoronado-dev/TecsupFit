package com.coronado.tecsupfit.data

data class Clase(
    val id: String,
    val nombre: String,
    val horario: String,
    val entrenador: String,
    val categoria: String,
    val dia: String,
    val cuposDisponibles: Int,
    val horariosPosibles: List<String>
)