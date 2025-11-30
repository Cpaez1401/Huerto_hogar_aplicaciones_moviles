package com.example.huertohogarappev2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogarappev2.data.UsuarioDao
import com.example.huertohogarappev2.model.Usuario
import kotlinx.coroutines.launch

class RegistroViewModel(
    private val usuarioDao: UsuarioDao
) : ViewModel() {

    // Estados que la pantalla observa
    var registroExitoso by mutableStateOf(false)
        private set

    var mensajeError by mutableStateOf<String?>(null)
        private set

    // LIMPIAR ERROR
    fun limpiarError() {
        mensajeError = null
    }

    // MOSTRAR ERROR
    fun mostrarError(msg: String) {
        mensajeError = msg
    }

    // REGISTRAR USUARIO
    fun registrar(
        nombre: String,
        correo: String,
        contrasena: String,
        direccion: String,
        telefono: String
    ) {
        viewModelScope.launch {

            // Validar si el correo existe
            val existe = usuarioDao.obtenerPorCorreo(correo)
            if (existe != null) {
                mensajeError = "El correo ya está registrado"
                return@launch
            }

            // Crear usuario nuevo
            val usuario = Usuario(
                nombre = nombre,
                correo = correo,
                contrasena = contrasena,
                direccion = direccion,
                telefono = telefono,
                activo = true
            )

            usuarioDao.insertar(usuario)

            mensajeError = null
            registroExitoso = true
        }
    }
}