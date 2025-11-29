package com.example.huertohogarappev2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogarappev2.data.UsuarioDao
import kotlinx.coroutines.launch


class LoginViewModel(private val usuarioDao: UsuarioDao) : ViewModel() {

    var correo by mutableStateOf("")
        private set

    var contrasena by mutableStateOf("")
        private set

    var loginExitoso by mutableStateOf(false)
        private set

    var error by mutableStateOf("")
        private set

    fun actualizarCorreo(nuevo: String) {
        correo = nuevo
    }

    fun actualizarContrasena(nueva: String) {
        contrasena = nueva
    }

    fun validarLogin() {
        viewModelScope.launch {
            val usuario = usuarioDao.obtenerPorCorreo(correo)
            if (usuario != null && usuario.contrasena == contrasena) {
                loginExitoso = true
                error = ""
            } else {
                loginExitoso = false
                error = "Datos incorrectos"
            }
        }
    }
}



