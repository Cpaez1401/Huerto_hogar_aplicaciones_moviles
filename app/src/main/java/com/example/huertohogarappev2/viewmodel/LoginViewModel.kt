package com.example.huertohogarappev2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogarappev2.data.UsuarioDao
import com.example.huertohogarappev2.model.Usuario
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

    var usuarioActual by mutableStateOf<Usuario?>(null)
        private set

    var usuarioActualId by mutableStateOf<Int?>(null)
        private set


    fun actualizarCorreo(nuevo: String) {
        correo = nuevo
    }

    fun actualizarContrasena(nueva: String) {
        contrasena = nueva
    }

    fun validarLogin() {
        viewModelScope.launch {

            // Buscar usuario por correo
            val user = usuarioDao.obtenerPorCorreo(correo)

            if (user != null && user.contrasena == contrasena) {
                usuarioActual = user
                usuarioActualId = user.id
                loginExitoso = true
                error = ""
            } else {
                usuarioActual = null
                loginExitoso = false
                error = "Datos incorrectos"
            }
        }
    }
}




