// /app/src/main/java/com/example/huertohogarappev2/ui/screens/PerfilScreen.kt
package com.example.huertohogarappev2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.huertohogarappev2.ui.components.TituloText

// Nota: Este es un ejemplo básico. Necesitarás un UsuarioViewModel para cargar los datos reales.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    navController: NavController
    // usuarioViewModel: UsuarioViewModel = viewModel()
) {
    // Simulación de datos de usuario
    val nombreUsuario = "Juan Pérez"
    val correoUsuario = "juan@huertohogar.cl"
    val direccionUsuario = "Av. Siempre Viva 123, Santiago"
    val telefonoUsuario = "+569 1234 5678"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
                actions = {
                    IconButton(onClick = { /* Navegar a Edición de Perfil */ }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Editar Perfil")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            // Card para mostrar la información del perfil
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    TituloText(nombreUsuario)
                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Correo Electrónico:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(correoUsuario, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Teléfono:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(telefonoUsuario, fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Dirección:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(direccionUsuario, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { /* Lógica para cerrar sesión */ }) {
                Text("Cerrar Sesión")
            }
        }
    }
}
