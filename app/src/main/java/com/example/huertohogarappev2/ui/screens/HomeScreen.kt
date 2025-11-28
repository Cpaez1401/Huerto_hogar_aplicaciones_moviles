package com.example.huertohogarappev2.ui.screens

import android.R.attr.fontWeight
import android.R.attr.text
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.huertohogarappev2.R
import com.example.huertohogarappev2.model.Producto
import com.example.huertohogarappev2.ui.components.TituloText
import com.example.huertohogarappev2.viewmodel.ProductoViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: ProductoViewModel = viewModel()
    val productos by viewModel.productos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarProductos()
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {
        TituloText(
            texto = "Bienvenido a Huerto Hogar"
        )


        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Productos frescos disponibles",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Carrusel
        com.example.huertohogarappev2.ui.components.CarruselSimple()

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de productos
        LazyColumn {
            items(productos) { producto ->
                ProductoItem(producto = producto, navController = navController)
            }
        }
    }
}

@Composable
fun ProductoItem(producto: Producto, navController: NavController) {
    val context = LocalContext.current
    val imageResource = obtenerImagen(context, producto.imagen)

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Imagen de ${producto.nombre}",
            modifier = Modifier
                .size(100.dp)
                .padding(end = 16.dp)
        )

        Column {
            Text(
                text = producto.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "$${producto.precio} CLP",
                color = Color(0xFF2F5D08),
                fontWeight = FontWeight.Medium
            )
            Text(
                text = producto.descripcion,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2
            )
            Text(
                text = "Stock: ${producto.stock}",
                fontSize = 12.sp,
                color = if (producto.stock > 0) Color.Green else Color.Red
            )
        }
    }
}

private fun obtenerImagen(context: Context, imagen: String?): Int {
    val nombre = imagen?.replace(".jpg", "")?.replace(".webp", "") ?: "not_found"
    val resourceId = context.resources.getIdentifier(nombre, "drawable", context.packageName)

    return if (resourceId == 0) R.drawable.not_found else resourceId
}


// PREVIEW CON DATOS DE EJEMPLO
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    com.example.huertohogarappev2.ui.theme.HuertoHogarAppEv2Theme {

        val productosEjemplo = listOf(
            Producto(
                id = 1,
                nombre = "Manzanas Fuji",
                descripcion = "Manzanas crujientes y dulces del Valle del Maule",
                precio = 1200,
                imagen = "fuji_red.jpg",
                stock = 150,
                categoria = "Frutas Frescas",
                unidad = "kilo",
                activo = true
            ),
            Producto(
                id = 2,
                nombre = "Naranjas Valencia",
                descripcion = "Naranjas jugosas ricas en vitamina C",
                precio = 1000,
                imagen = "naranjas_valencia.webp",
                stock = 200,
                categoria = "Frutas Frescas",
                unidad = "kilo",
                activo = true
            ),
            Producto(
                id = 3,
                nombre = "Zanahorias Orgánicas",
                descripcion = "Zanahorias cultivadas sin pesticidas en O'Higgins",
                precio = 900,
                imagen = "zanahorias_organicas.jpg",
                stock = 100,
                categoria = "Verduras Orgánicas",
                unidad = "kilo",
                activo = true
            )
        )

        Column(
            modifier = Modifier
                .padding(27.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Bienvenido a Huerto Hogar",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2F5D08)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Productos frescos disponibles",
                fontSize = 18.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Carrusel
            com.example.huertohogarappev2.ui.components.CarruselSimple()

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de productos de ejemplo
            LazyColumn {
                items(productosEjemplo) { producto ->
                    ProductoItemPreviewContent(producto = producto)
                }
            }
        }
    }
}

// Versión simplificada para el Preview
@Composable
fun ProductoItemPreviewContent(producto: Producto) {
    val context = LocalContext.current
    val imageResource = obtenerImagen(context, producto.imagen)

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Imagen de ${producto.nombre}",
            modifier = Modifier
                .size(100.dp)
                .padding(end = 16.dp)
        )

        Column {
            Text(
                text = producto.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "$${producto.precio} CLP",
                color = Color(0xFF2F5D08),
                fontWeight = FontWeight.Medium
            )
            Text(
                text = producto.descripcion,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2
            )
            Text(
                text = "Stock: ${producto.stock}",
                fontSize = 12.sp,
                color = if (producto.stock > 0) Color.Green else Color.Red
            )
        }
    }
}


