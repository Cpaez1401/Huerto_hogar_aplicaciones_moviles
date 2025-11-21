package com.example.huertohogarappev2.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.huertohogarappev2.ui.components.BotonPrincipal


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CarritoScreen(
    onConfirmarPedido: () -> Unit,
    onVolver: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Carrito") })
        }
    ) { padding ->

        Column(
            modifier = Modifier.padding(padding).padding(24.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(3) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Producto $index")
                            Text("Cantidad: 1")
                            Text("Precio: $1000")
                        }
                    }
                }
            }

            BotonPrincipal("Confirmar Pedido", onClick = onConfirmarPedido)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedButton(onClick = onVolver, modifier = Modifier.fillMaxWidth()) {
                Text("Volver")
            }
        }
    }
}