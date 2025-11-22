package com.example.huertohogarappev2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var correo by mutableStateOf("")
        private set

    var contrasena by mutableStateOf("")
        private set

    var loginExitoso by mutableStateOf(false)
        private set

    fun actualizarCorreo(nuevo: String) {
        correo = nuevo
    }

    fun actualizarContrasena(nueva: String) {
        contrasena = nueva
    }

    fun validarLogin() {
        // Ejemplo simple, igual al estilo PokeStore
        loginExitoso = correo == "test@huerto.cl" && contrasena == "1234"
    }
}
