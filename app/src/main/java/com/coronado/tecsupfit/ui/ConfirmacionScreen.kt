package com.coronado.tecsupfit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coronado.tecsupfit.data.Clase

@Composable
fun ConfirmacionScreen(
    clase: Clase?,
    horario: String,
    onConfirmar: (Clase, String) -> Unit,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (clase == null) {
        Text(
            text = "No se encontro la clase.",
            modifier = modifier.padding(16.dp)
        )
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Confirma tu reserva",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Revisa el resumen antes de confirmar.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Resumen de la reserva",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Clase: ${clase.nombre}")
                Text(text = "Entrenador: ${clase.entrenador}")
                Text(text = "Dia: ${clase.dia}")
                Text(
                    text = "Horario: $horario",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onConfirmar(clase, horario) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("CONFIRMAR RESERVA")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al detalle")
        }
    }
}