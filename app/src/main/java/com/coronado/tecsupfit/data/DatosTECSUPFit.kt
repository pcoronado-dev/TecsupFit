package com.coronado.tecsupfit.data

object DatosTECSUPFit {

    val clases = listOf(
        Clase(
            id = "spinning",
            nombre = "Spinning",
            horario = "08:00",
            entrenador = "Lucia Ramirez",
            categoria = "Cardio",
            dia = "Hoy",
            cuposDisponibles = 4,
            horariosPosibles = listOf("08:00", "10:00", "18:00")
        ),
        Clase(
            id = "crossfit",
            nombre = "CrossFit",
            horario = "09:00",
            entrenador = "Carlos Mendoza",
            categoria = "Fuerza",
            dia = "Hoy",
            cuposDisponibles = 2,
            horariosPosibles = listOf("09:00", "12:00", "19:00")
        ),
        Clase(
            id = "yoga",
            nombre = "Yoga Matinal",
            horario = "07:30",
            entrenador = "Ana Torres",
            categoria = "Flexibilidad",
            dia = "Esta semana",
            cuposDisponibles = 8,
            horariosPosibles = listOf("07:30", "17:00", "20:00")
        ),
        Clase(
            id = "boxeo",
            nombre = "Boxeo Funcional",
            horario = "11:00",
            entrenador = "Miguel Vega",
            categoria = "Cardio",
            dia = "Esta semana",
            cuposDisponibles = 5,
            horariosPosibles = listOf("11:00", "14:00", "18:30")
        )
    )

    val rutinas = listOf(
        Rutina("Rutina Full Body", "30 min", "3 rondas", "Gana fuerza general con 8 ejercicios basicos."),
        Rutina("Quema Grasa HIIT", "20 min", "4 rondas", "Intervalos intensos para acelerar tu metabolismo."),
        Rutina("Core y Equilibrio", "25 min", "2 rondas", "Fortalece tu abdomen y mejora tu estabilidad."),
        Rutina("Lower Body", "35 min", "3 rondas", "Trabaja piernas y gluteos con foco en resistencia.")
    )

    val nombreUsuario = "Piero Alexander Coronado Quispe"
    val membresia = "Premium Anual"
    val miembroDesde = "Marzo 2025"

    fun buscarClase(id: String): Clase? = clases.firstOrNull { it.id == id }


    data class Rutina(
        val nombre: String,
        val duracion: String,
        val rondas: String,
        val descripcion: String
    )
}