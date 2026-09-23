package com.coronado.tecsupfit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coronado.tecsupfit.data.EstadoReserva
import com.coronado.tecsupfit.data.Reserva
import com.coronado.tecsupfit.ui.theme.GrisCompletada
import com.coronado.tecsupfit.ui.theme.RojoCancelada
import com.coronado.tecsupfit.ui.theme.VerdeConfirmada

@Composable
fun ReservasScreen(
    reservas: List<Reserva>,
    onVerClases: () -> Unit,
    onCancelarReserva: (Reserva) -> Unit,
    modifier: Modifier = Modifier
) {
    var reservaSeleccionadaParaCancelar by remember { mutableStateOf<Reserva?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Mis reservas",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Aqui se listan tus clases reservadas.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (reservas.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Aun no tienes reservas.",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = onVerClases) {
                    Text("VER CLASES DISPONIBLES")
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(reservas, key = { it.nombreClase + it.horario }) { reserva ->
                    TarjetaReserva(
                        reserva = reserva,
                        onCancelar = { reservaSeleccionadaParaCancelar = reserva }
                    )
                }
            }
        }
    }

    reservaSeleccionadaParaCancelar?.let { reserva ->
        AlertDialog(
            onDismissRequest = { reservaSeleccionadaParaCancelar = null },
            title = { Text("Cancelar reserva") },
            text = {
                Text("¿Deseas cancelar tu reserva para la clase de \"${reserva.nombreClase}\" a las ${reserva.horario}?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        onCancelarReserva(reserva)
                        reservaSeleccionadaParaCancelar = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text("Sí, cancelar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { reservaSeleccionadaParaCancelar = null }
                ) {
                    Text("Volver")
                }
            }
        )
    }
}

@Composable
fun TarjetaReserva(
    reserva: Reserva,
    onCancelar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val estadoTexto = when (reserva.estado) {
        EstadoReserva.CONFIRMADA -> "Confirmada"
        EstadoReserva.COMPLETADA -> "Completada"
        EstadoReserva.CANCELADA -> "Cancelada"
    }

    val colorEstado = when (reserva.estado) {
        EstadoReserva.CONFIRMADA -> VerdeConfirmada
        EstadoReserva.COMPLETADA -> GrisCompletada
        EstadoReserva.CANCELADA -> RojoCancelada
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = reserva.nombreClase,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Horario: ${reserva.horario}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Surface(
                    color = colorEstado,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = estadoTexto,
                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            if (reserva.estado == EstadoReserva.CONFIRMADA) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onCancelar,
                    modifier = Modifier.align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Text("Cancelar reserva")
                }
            }
        }
    }
}
