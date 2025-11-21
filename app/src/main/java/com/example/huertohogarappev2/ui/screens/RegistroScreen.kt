package com.example.huertohogarappev2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.huertohogarappev2.ui.components.BotonPrincipal
import com.example.huertohogarappev2.ui.components.CampoTexto
import com.example.huertohogarappev2.ui.components.TituloText

@Composable
fun RegistroScreen(
    onRegistroExitoso: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TituloText("Crear Cuenta")

        Spacer(modifier = Modifier.height(16.dp))

        CampoTexto("Nombre", nombre, { nombre = it })
        CampoTexto("Correo", correo, { correo = it })
        CampoTexto("Contraseña", contrasena, { contrasena = it })
        CampoTexto("Dirección", direccion, { direccion = it })
        CampoTexto("Teléfono", telefono, { telefono = it })

        Spacer(modifier = Modifier.height(24.dp))

        BotonPrincipal("Registrarse", onClick = {
            // Aquí irá el registro con ViewModel
            onRegistroExitoso()
        }, modifier = Modifier.fillMaxWidth())
    }
}
