package com.example.huertohogarappev2.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.huertohogarappev2.R
import com.example.huertohogarappev2.ui.components.TituloText
import com.example.huertohogarappev2.viewmodel.ProductoViewModel


@Composable
fun ProductosScreen(
    navController: NavController,
    viewModel: ProductoViewModel = viewModel()
) {

    val productos = viewModel.productos.collectAsState().value

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {

        TituloText("Productos Disponibles")

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Compra productos frescos y locales",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.bannerproductosfrescos),
            contentDescription = "Banner productos",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(productos) { producto ->
                ProductoItem(
                    producto = producto,
                    navController = navController
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "ProductosScreen Preview"
)
@Composable
fun ProductosScreenPreview() {

    // 1. NavController de prueba
    val navController = rememberNavController()

    // 2. ViewModel para la preview
    val viewModel: ProductoViewModel = viewModel()


    // 3. Llamamos a la pantalla real
    ProductosScreen(
        navController = navController,
        viewModel = viewModel
    )
}


