package com.example.huertohogarappev2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.huertohogarappev2.viewmodel.PerfilViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    navController: NavController,
    usuarioId: Int?,
    viewModel: PerfilViewModel
) {

    // Cargar el usuario cuando entramos a la pantalla
    LaunchedEffect(usuarioId) {
        usuarioId?.let { id ->
            viewModel.cargarUsuario(id)
        }
    }

    // Observar el usuario desde el ViewModel
    val usuario = viewModel.usuario

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Perfil") },
                actions = {
                    IconButton(onClick = { /* Navegar a edición */ }) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar Perfil")
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

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    // NOMBRE
                    Text(
                        text = usuario?.nombre ?: "Cargando...",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    // CORREO
                    Text("Correo Electrónico:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(usuario?.correo ?: "", fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(8.dp))


                    // TELÉFONO
                    Text("Teléfono:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(usuario?.telefono ?: "", fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(8.dp))


                    // DIRECCIÓN
                    Text("Dirección:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(usuario?.direccion ?: "", fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { /* Cerrar sesión */ }) {
                Text("Cerrar Sesión")
            }
        }
    }
}



