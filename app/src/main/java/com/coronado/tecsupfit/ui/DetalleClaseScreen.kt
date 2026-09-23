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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.coronado.tecsupfit.data.Clase
import com.coronado.tecsupfit.ui.theme.VerdeOscuro


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
            .padding(20.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = clase.nombre,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Entrenador: ${clase.entrenador}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "${clase.categoria} · ${clase.dia}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(8.dp))
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
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Selección única de horario (chips tipo pill)
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(clase.horariosPosibles) { horario ->
                val seleccionado = horarioSeleccionado == horario
                FilterChip(
                    selected = seleccionado,
                    onClick = { horarioSeleccionado = horario },
                    label = { Text(horario) },
                    shape = RoundedCornerShape(50),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = VerdeOscuro,
                        selectedLabelColor = Color.White,
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        labelColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = seleccionado,
                        borderColor = MaterialTheme.colorScheme.outline,
                        selectedBorderColor = VerdeOscuro
                    )
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onReservar(clase, horarioSeleccionado) },
            enabled = horarioSeleccionado.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeOscuro)
        ) {
            Text("RESERVAR CUPO", style = MaterialTheme.typography.labelLarge, color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = if (horarioSeleccionado.isEmpty()) {
                "Selecciona un horario para habilitar la reserva."
            } else {
                "Horario elegido: $horarioSeleccionado"
            },
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}
