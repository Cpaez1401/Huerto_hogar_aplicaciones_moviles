package com.example.huertohogarappev2.ui.screen

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.huertohogarappev2.viewmodel.CarritoViewModel
import com.example.huertohogarappev2.viewmodel.ProductoViewModel


@Composable
fun CarritoScreen(
    navController: NavHostController,
    carritoViewModel: CarritoViewModel,
    productoViewModel: ProductoViewModel
) {
    val carrito = carritoViewModel.carrito.collectAsState()
    val productos = productoViewModel.productos.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Carrito de Compras",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (carrito.value.isEmpty()) {
            Text("Tu carrito está vacío")
        } else {
            LazyColumn {
                items(carrito.value) { item ->


                    val producto = productos.value.find { it.id == item.productoId }

                    if (producto != null) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = producto.nombre,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(text = "$${producto.precio}")
                                }

                                IconButton(onClick = {
                                    carritoViewModel.eliminarDelCarrito(producto.id)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Eliminar"
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { carritoViewModel.limpiarCarrito() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF4CAF50))
            ) {
                Text("Vaciar carrito")
            }
        }
    }
}