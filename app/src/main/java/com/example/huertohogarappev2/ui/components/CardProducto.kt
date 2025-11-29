package com.example.huertohogarappev2.ui.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.huertohogarappev2.R
import com.example.huertohogarappev2.model.Producto


@Composable
fun CardProducto(
    producto: Producto,
    onAgregar: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val imagenProducto = obtenerImagen(context, producto.imagen)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            // Imagen del producto
            Image(
                painter = painterResource(id = imagenProducto),
                contentDescription = producto.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Nombre
            Text(
                text = producto.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            // Precio
            Text(
                text = "${producto.precio} CLP",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4CAF50)
            )

            // Descripción corta
            Text(
                text = producto.descripcion,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Botón solo si onAgregar != null
            if (onAgregar != null) {
                BotonPrincipal(
                    texto = "Agregar al carrito",
                    color = Color(0xFF4CAF50),
                    onClick = onAgregar
                )
            }
        }
    }
}




private fun obtenerImagen(context: Context, imagen: String?): Int {
    val nombre = imagen?.replace(".jpg", "")?.replace(".webp", "") ?: "not_found"
    val resourceId = context.resources.getIdentifier(nombre, "drawable", context.packageName)
    return if (resourceId == 0) R.drawable.not_found else resourceId
}