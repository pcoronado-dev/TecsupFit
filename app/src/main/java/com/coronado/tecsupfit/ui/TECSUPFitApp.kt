package com.coronado.tecsupfit.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.coronado.tecsupfit.data.DatosTECSUPFit
import com.coronado.tecsupfit.data.EstadoReserva
import com.coronado.tecsupfit.data.Reserva
import kotlin.collections.contains

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TECSUPFitApp() {
    val navController = rememberNavController()
    val reservas = remember {
        mutableStateListOf(
            Reserva("Spinning", "08:00", EstadoReserva.COMPLETADA)
        )
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    val mostrarBottomBar = rutaActual in listOf(Rutas.INICIO, Rutas.RESERVAS, Rutas.RUTINAS, Rutas.PERFIL)
    val tituloTopBar = tituloParaRuta(rutaActual)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(tituloTopBar) },
                navigationIcon = {
                    if (!mostrarBottomBar && navController.previousBackStackEntry != null) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver"
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            if (mostrarBottomBar) {
                TECSUPFitBottomBar(
                    navController = navController,
                    rutaActual = rutaActual
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Rutas.INICIO,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Rutas.INICIO) {
                InicioScreen(
                    onClaseClick = { clase ->
                        navController.navigate(Rutas.detalle(clase.id))
                    }
                )
            }

            composable(
                route = Rutas.DETALLE,
                arguments = listOf(navArgument("claseId") { type = NavType.StringType })
            ) { entry ->
                val claseId = entry.arguments?.getString("claseId").orEmpty()
                DetalleClaseScreen(
                    clase = DatosTECSUPFit.buscarClase(claseId),
                    onReservar = { clase, horario ->
                        navController.navigate(Rutas.confirmacion(clase.id, horario))
                    }
                )
            }

            composable(
                route = Rutas.CONFIRMACION,
                arguments = listOf(
                    navArgument("claseId") { type = NavType.StringType },
                    navArgument("horario") { type = NavType.StringType }
                )
            ) { entry ->
                val claseId = entry.arguments?.getString("claseId").orEmpty()
                val horario = entry.arguments?.getString("horario").orEmpty()
                ConfirmacionScreen(
                    clase = DatosTECSUPFit.buscarClase(claseId),
                    horario = horario,
                    onConfirmar = { clase, horarioReservado ->
                        reservas.add(
                            Reserva(
                                nombreClase = clase.nombre,
                                horario = horarioReservado,
                                estado = EstadoReserva.CONFIRMADA
                            )
                        )
                        navController.navegarAPestana(Rutas.RESERVAS)
                    },
                    onVolver = { navController.navigateUp() }
                )
            }

            composable(Rutas.RESERVAS) {
                ReservasScreen(
                    reservas = reservas.toList(),
                    onVerClases = { navController.navegarAPestana(Rutas.INICIO) }
                )
            }

            composable(Rutas.RUTINAS) {
                RutinasScreen()
            }

            composable(Rutas.PERFIL) {
                PerfilScreen(reservas = reservas.toList())
            }
        }
    }
}