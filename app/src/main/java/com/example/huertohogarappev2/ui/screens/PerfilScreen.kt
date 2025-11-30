package com.example.huertohogarappev2.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.huertohogarappev2.ui.components.BotonPrincipal
import com.example.huertohogarappev2.viewmodel.PerfilViewModel

@Composable
fun PerfilScreen(
    navController: NavController,
    viewModel: PerfilViewModel
) {
    val usuario = viewModel.usuario.collectAsState().value

    if (usuario == null) {
        Text("Cargando perfil...", modifier = Modifier.padding(24.dp))
        return
    }

    var nombre by remember { mutableStateOf(usuario.nombre) }
    var direccion by remember { mutableStateOf(usuario.direccion) }
    var telefono by remember { mutableStateOf(usuario.telefono) }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Perfil de Usuario", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        BotonPrincipal(
            texto = "Guardar cambios",
            onClick = {
                viewModel.actualizarPerfil(nombre, direccion, telefono)
            }
        )
    }
}
