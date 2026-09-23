package com.coronado.tecsupfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coronado.tecsupfit.data.EstadoReserva
import com.coronado.tecsupfit.data.Reserva
import com.coronado.tecsupfit.ui.theme.GrisCompletada
import com.coronado.tecsupfit.ui.theme.RojoCancelada
import com.coronado.tecsupfit.ui.theme.VerdeClaroFondo
import com.coronado.tecsupfit.ui.theme.VerdeConfirmada
import com.coronado.tecsupfit.ui.theme.VerdeOscuro

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
            .padding(20.dp)
    ) {
        Text(
            text = "Mis reservas",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Aquí se listan tus clases reservadas.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (reservas.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Aún no tienes reservas.",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onVerClases,
                    modifier = Modifier.height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeOscuro)
                ) {
                    Text("VER CLASES DISPONIBLES", style = MaterialTheme.typography.labelLarge, color = Color.White)
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
                        containerColor = RojoCancelada,
                        contentColor = Color.White
                    )
                ) {
                    Text("Sí, cancelar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { reservaSeleccionadaParaCancelar = null }
                ) {
                    Text("Volver", color = MaterialTheme.colorScheme.onSurfaceVariant)
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
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
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
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(VerdeClaroFondo),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        tint = VerdeOscuro,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

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
                    color = when (reserva.estado) {
                        EstadoReserva.CONFIRMADA -> VerdeClaroFondo
                        EstadoReserva.COMPLETADA -> GrisCompletada.copy(alpha = 0.2f)
                        EstadoReserva.CANCELADA -> Color(0xFFFFEBEE)
                    },
                    shape = RoundedCornerShape(50)
                ) {
                    Text(
                        text = estadoTexto,
                        modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp),
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = colorEstado
                    )
                }
            }

            if (reserva.estado == EstadoReserva.CONFIRMADA) {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = onCancelar,
                    modifier = Modifier
                        .align(Alignment.End)
                        .height(36.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFEBEE),
                        contentColor = RojoCancelada
                    )
                ) {
                    Text("Cancelar reserva", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}
