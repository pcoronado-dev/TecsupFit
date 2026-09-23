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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coronado.tecsupfit.data.Clase
import com.coronado.tecsupfit.data.DatosTECSUPFit

@Composable
fun InicioScreen(
    onClaseClick: (Clase) -> Unit,
    modifier: Modifier = Modifier
) {
    var filtroSeleccionado by remember { mutableStateOf("Hoy") }

    val clasesFiltradas = if (filtroSeleccionado == "Hoy") {
        DatosTECSUPFit.clases.filter { it.dia == "Hoy" }
    } else {
        DatosTECSUPFit.clases
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Reserva tu clase",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Elige el dia y encuentra tu entrenamiento.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Fila de filtros (LazyRow) con seleccion unica: "Hoy" / "Esta semana"
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf("Hoy", "Esta semana")) { filtro ->
                FilterChip(
                    selected = filtroSeleccionado == filtro,
                    onClick = { filtroSeleccionado = filtro },
                    label = { Text(filtro) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Lista principal de clases (LazyColumn)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(clasesFiltradas, key = { it.id }) { clase ->
                TarjetaClase(
                    clase = clase,
                    onClick = { onClaseClick(clase) }
                )
            }
        }
    }
}

@Composable
fun TarjetaClase(
    clase: Clase,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = clase.nombre,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Entrenador: ${clase.entrenador}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${clase.categoria} · ${clase.horario}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = if (clase.cuposDisponibles > 0) {
                    "${clase.cuposDisponibles} cupos"
                } else {
                    "Sin cupo"
                },
                style = MaterialTheme.typography.labelLarge,
                color = if (clase.cuposDisponibles > 0) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.error
                }
            )
        }
    }
}