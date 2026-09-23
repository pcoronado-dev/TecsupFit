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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coronado.tecsupfit.data.DatosTECSUPFit
import com.coronado.tecsupfit.data.EstadoReserva
import com.coronado.tecsupfit.data.Reserva
import com.coronado.tecsupfit.ui.theme.VerdeOscuro

@Composable
fun PerfilScreen(
    reservas: List<Reserva>,
    modifier: Modifier = Modifier
) {
    val clasesTomadas = reservas.count { it.estado == EstadoReserva.COMPLETADA }
    val reservasActivas = reservas.count { it.estado == EstadoReserva.CONFIRMADA }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Avatar de perfil
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(VerdeOscuro),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TF",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = DatosTECSUPFit.nombreUsuario,
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "Membresía: ${DatosTECSUPFit.membresia} · Miembro desde ${DatosTECSUPFit.miembroDesde}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TarjetaEstadistica(
                valor = "$clasesTomadas",
                etiqueta = "Clases tomadas",
                modifier = Modifier.weight(1f)
            )

            TarjetaEstadistica(
                valor = "4 días",
                etiqueta = "Racha",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        TarjetaEstadistica(
            valor = "$reservasActivas",
            etiqueta = "Reservas activas",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TarjetaEstadistica(
    valor: String,
    etiqueta: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = valor,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = etiqueta,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
