package com.coronado.tecsupfit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coronado.tecsupfit.data.Clase


@Composable
fun DetalleClaseScreen(
    clase: Clase?,
    onReservar: (Clase, String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (clase == null) {
        Text(
            text = "No se encontro la clase.",
            modifier = modifier.padding(16.dp)
        )
        return
    }

    var horarioSeleccionado by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = clase.nombre,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Entrenador: ${clase.entrenador}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${clase.categoria} · ${clase.dia}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "${clase.cuposDisponibles} cupos disponibles",
                    style = MaterialTheme.typography.labelLarge,
                    color = if (clase.cuposDisponibles > 0) {
                        MaterialTheme.colorScheme.secondary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Elige tu horario",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Selecciona un solo horario para reservar.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Seleccion unica de horario (chips que funcionan como RadioButton)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(clase.horariosPosibles) { horario ->
                FilterChip(
                    selected = horarioSeleccionado == horario,
                    onClick = { horarioSeleccionado = horario },
                    label = { Text(horario) }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onReservar(clase, horarioSeleccionado) },
            enabled = horarioSeleccionado.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("RESERVAR CUPO")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (horarioSeleccionado.isEmpty()) {
                "Selecciona un horario para habilitar la reserva."
            } else {
                "Horario elegido: $horarioSeleccionado"
            },
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline
        )
    }
}