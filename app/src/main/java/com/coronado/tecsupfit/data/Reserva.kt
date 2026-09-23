package com.coronado.tecsupfit.data

enum class EstadoReserva {
    CONFIRMADA,
    COMPLETADA,
    CANCELADA
}

data class Reserva(
    val nombreClase: String,
    val horario: String,
    val estado: EstadoReserva
)
