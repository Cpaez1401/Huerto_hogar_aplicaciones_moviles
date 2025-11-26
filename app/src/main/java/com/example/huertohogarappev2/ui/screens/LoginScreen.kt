package com.example.huertohogarappev2.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.huertohogarappev2.ui.components.BotonPrincipal
import com.example.huertohogarappev2.ui.components.CampoTexto
import com.example.huertohogarappev2.ui.components.TituloText

@Preview(showBackground = true, showSystemUi = true, name = "login",
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun LoginScreen(
    onLogin: () -> Unit = {},
    onGoToRegister: () -> Unit = {}
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {

            TituloText("Inciar Sesión")

            CampoTexto(
                valor = correo,
                onValueChange = { correo = it },
                label = "Correo"
            )

            CampoTexto(
                valor = contrasena,
                onValueChange = { contrasena = it },
                label = "Contraseña",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            BotonPrincipal(
                texto = "Ingresar",
                color = Color(0xFFC138636),
                onClick = { onLogin() }
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = onGoToRegister) {
                Text("Crear cuenta")
            }
        }
    }
}

