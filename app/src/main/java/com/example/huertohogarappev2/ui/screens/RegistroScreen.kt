package com.example.huertohogarappev2.ui.screen


import android.R.attr.onClick
import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.huertohogarappev2.R
import com.example.huertohogarappev2.ui.components.BotonPrincipal
import com.example.huertohogarappev2.ui.components.CampoTexto
import com.example.huertohogarappev2.ui.components.TituloText
import com.example.huertohogarappev2.viewmodel.RegistroViewModel

/*@Preview(showBackground = true, showSystemUi = true)*/
@Composable
fun RegistroScreen(
    viewModel: RegistroViewModel,
    onRegistrar: () -> Unit = {},
    onIrLogin: () -> Unit = {}
) {
    val registroExitoso = viewModel.registroExitoso.collectAsState()
    val mensajeError = viewModel.mensajeError.collectAsState()


    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var confirmarContrasena by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }

    LaunchedEffect(registroExitoso.value) {
        if (registroExitoso.value) {
            onRegistrar()
        }
    }

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
    ) {

        TituloText(texto = "Bienvenido a Huerto Hogar")

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Productos frescos disponibles",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))


        Image(
            painter = painterResource(id = R.drawable.mercadoonline),
            contentDescription = "Banner Mercado Online",
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp) // más pequeño para compactar
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )


        Spacer(modifier = Modifier.height(30.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            TituloText("Crear Cuenta")

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre
            CampoTexto(
                valor = nombre,
                onValueChange = { nombre = it },
                label = "Nombre"
            )

            // Correo
            CampoTexto(
                valor = correo,
                onValueChange = { correo = it },
                label = "Correo"
            )

            // Contraseña
            CampoTexto(
                valor = contrasena,
                onValueChange = { contrasena = it },
                label = "Contraseña",
                isPassword = true
            )

            // Confirmar Contraseña
            CampoTexto(
                valor = confirmarContrasena,
                onValueChange = { confirmarContrasena = it },
                label = "Confirmar Contraseña",
                isPassword = true
            )

            // Dirección
            CampoTexto(
                valor = direccion,
                onValueChange = { direccion = it },
                label = "Dirección"
            )

            // Teléfono
            CampoTexto(
                valor = telefono,
                onValueChange = { telefono = it },
                label = "Teléfono"
            )
            if (mensajeError.value != null) {
                Text(
                    text = mensajeError.value!!,
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            BotonPrincipal(
                texto = "Registrarse",
                color = Color(0xFF4CAF50),
                onClick = {

                    when {
                        nombre.isBlank() || correo.isBlank() ||
                                contrasena.isBlank() || confirmarContrasena.isBlank() ||
                                direccion.isBlank() || telefono.isBlank() -> {

                            viewModel.mostrarError("Por favor, complete todos los campos")
                        }

                        contrasena != confirmarContrasena -> {
                            viewModel.mostrarError("Las contraseñas no coinciden")
                        }

                        else -> {
                            viewModel.registrar(
                                nombre,
                                correo,
                                contrasena,
                                direccion,
                                telefono
                            )
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = onIrLogin) {
                Text("¿Ya tienes cuenta? Inicia sesión")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistroScreenPreview() {
    // Fake ViewModel para Preview
    val fakeViewModel = object : RegistroViewModel(Application()) {}

    RegistroScreen(
        viewModel = fakeViewModel,
        onRegistrar = {},
        onIrLogin = {}
    )
}


