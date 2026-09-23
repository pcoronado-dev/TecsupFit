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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.coronado.tecsupfit.data.Clase
import com.coronado.tecsupfit.data.DatosTECSUPFit
import com.coronado.tecsupfit.ui.theme.VerdeClaroFondo
import com.coronado.tecsupfit.ui.theme.VerdeOscuro

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
            .padding(20.dp)
    ) {
        Text(
            text = "Reserva tu clase",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Elige el día y encuentra tu entrenamiento.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Fila de filtros (LazyRow) tipo pill
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf("Hoy", "Esta semana")) { filtro ->
                val seleccionado = filtroSeleccionado == filtro
                FilterChip(
                    selected = seleccionado,
                    onClick = { filtroSeleccionado = filtro },
                    label = { Text(filtro) },
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
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
                    imageVector = Icons.Filled.FitnessCenter,
                    contentDescription = null,
                    tint = VerdeOscuro,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

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
