package com.example.huertohogarappev2.ui.screen

import android.app.Application
import androidx.compose.ui.text.font.FontWeight
import com.example.huertohogarappev2.ui.components.CardProducto
import com.example.huertohogarappev2.ui.theme.HuertoHogarAppEv2Theme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.huertohogarappev2.ui.components.BotonPrincipal
import com.example.huertohogarappev2.ui.components.TituloText
import com.example.huertohogarappev2.viewmodel.CarritoViewModel
import com.example.huertohogarappev2.viewmodel.ProductoViewModel


@Composable
fun CarritoScreen(
    navController: NavController,
    carritoViewModel: CarritoViewModel = viewModel(),
    productoViewModel: ProductoViewModel = viewModel()
) {
    val carrito = carritoViewModel.carrito.collectAsState().value
    val productos = productoViewModel.productos.collectAsState().value

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {

        // TÍTULO
        TituloText("Mi Carrito")

        Spacer(modifier = Modifier.height(16.dp))

        if (carrito.isEmpty()) {
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
            items(carrito) { item ->

                val producto = productos.find { it.id == item.productoId }

                if (producto != null) {
                    CardProducto(
                        producto = producto,
                        cantidad = item.cantidad,
                        onEliminar = { carritoViewModel.eliminarDelCarrito(item.productoId) }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // TOTAL
        val total = carrito.sumOf { item ->
            val p = productos.find { it.id == item.productoId }
            if (p != null) p.precio * item.cantidad else 0
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





