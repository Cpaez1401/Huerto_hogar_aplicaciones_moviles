package com.example.huertohogarappev2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.huertohogarappev2.R
import com.example.huertohogarappev2.ui.components.BotonPrincipal
import com.example.huertohogarappev2.ui.components.CampoTexto
import com.example.huertohogarappev2.ui.components.TituloText
import com.example.huertohogarappev2.viewmodel.LoginViewModel

/*@Preview(
    showBackground = true, showSystemUi = true, name = "login",
    device = "spec:width=411dp,height=891dp"
)*/
@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLogin: () -> Unit = {},
    onGoToRegister: () -> Unit = {}
) {
    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

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


            TituloText("Iniciar Sesión")

            Spacer(modifier = Modifier.height(8.dp))

            CampoTexto(
                valor = viewModel.correo,
                onValueChange = { viewModel.actualizarCorreo(it)},
                label = "Correo"
            )

            CampoTexto(
                valor = viewModel.contrasena,
                onValueChange = { viewModel.actualizarContrasena(it)},
                label = "Contraseña",
                isPassword = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            BotonPrincipal(
                texto = "Ingresar",
                color = Color(0xFF2F5D08),
                onClick = { viewModel.validarLogin()
                    if (viewModel.loginExitoso) {
                        onLogin()
                    } else {
                        print("Datos incorrectos")
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = onGoToRegister) {
                Text("Crear cuenta")
            }
        }
    }
}