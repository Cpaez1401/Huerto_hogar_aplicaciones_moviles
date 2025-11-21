package com.example.huertohogarappev2.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.huertohogarappev2.ui.components.BotonPrincipal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductoDetalleScreen(
    productoId: Int,
    onAgregarCarrito: () -> Unit,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle del Producto") })
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
        ) {
            Text("Producto ID: $productoId", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Descripción del producto…")

            Spacer(modifier = Modifier.height(24.dp))

            BotonPrincipal("Agregar al Carrito", onClick = onAgregarCarrito)

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(onClick = onVolver) {
                Text("Volver")
            }
        }
    }
}