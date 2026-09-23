package com.coronado.tecsupfit.data

enum class EstadoReserva {
    CONFIRMADA,
    COMPLETADA
}

data class Reserva(
    val nombreClase: String,
    val horario: String,
    val estado: com.coronado.tecsupfit.data.EstadoReserva
)