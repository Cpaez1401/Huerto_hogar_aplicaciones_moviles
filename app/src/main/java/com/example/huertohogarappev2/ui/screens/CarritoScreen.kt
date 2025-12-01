package com.example.huertohogarappev2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.huertohogarappev2.ui.components.BotonPrincipal
import com.example.huertohogarappev2.ui.components.CardProducto
import com.example.huertohogarappev2.ui.components.TituloText
import com.example.huertohogarappev2.viewmodel.CarritoViewModel


@Composable
fun CarritoScreen(
    navController: NavController,
    carritoViewModel: CarritoViewModel = viewModel(),

) {

    val carritoItems = carritoViewModel.carritoConProductos.collectAsState().value

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {

        // TÍTULO
        TituloText("Mi Carrito")

        Spacer(modifier = Modifier.height(16.dp))

        if (carritoItems.isEmpty()) {
            Text(
                text = "Tu carrito está vacío 😢",
                fontSize = 18.sp
            )
            return
        }

        // LISTA DE PRODUCTOS
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(carritoItems) { item ->

                val producto = item.producto ?: return@items  // Evita crash si es null

                CardProducto(
                    producto = producto,
                    cantidad = item.carrito.cantidad,
                    onEliminar = { carritoViewModel.eliminarDelCarrito(producto.id) }
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // TOTAL
        val total = carritoItems.sumOf { item ->
            item.producto?.precio?.times(item.carrito.cantidad) ?: 0
        }

        Text(
            text = "Total: $total CLP",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // BOTÓN FINALIZAR COMPRA
        BotonPrincipal(
            texto = "Finalizar compra",
            color = Color(0xFF4CAF50),
            onClick = {
                carritoViewModel.limpiarCarrito()
                navController.navigate("home")
            }
        )
    }

}