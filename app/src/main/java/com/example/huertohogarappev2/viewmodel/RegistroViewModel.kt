package com.example.huertohogarappev2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogarappev2.data.HuertoHogarDatabase
import com.example.huertohogarappev2.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class RegistroViewModel(application: Application) : AndroidViewModel(application) {

    private val usuarioDao = HuertoHogarDatabase.getDatabase(application).usuarioDao()


    private val _registroExitoso = MutableStateFlow(false)
    val registroExitoso = _registroExitoso.asStateFlow()

    private val _mensajeError = MutableStateFlow<String?>(null)
    val mensajeError = _mensajeError.asStateFlow()

    fun registrar(
        nombre: String,
        correo: String,
        contrasena: String,
        direccion: String,
        telefono: String
    ) {
        viewModelScope.launch {

            // 1. Validar si el correo ya existe
            val existente = usuarioDao.obtenerPorCorreo(correo)
            if (existente != null) {
                _mensajeError.value = "El correo ya está registrado"
                return@launch
            }

            // 2. Crear usuario
            val usuario = Usuario(
                nombre = nombre,
                correo = correo,
                contrasena = contrasena,
                direccion = direccion,
                telefono = telefono,
                activo = true
            )

            usuarioDao.insertar(usuario)

            _mensajeError.value = null
            _registroExitoso.value = true
        }
    }

    fun limpiarError() {
        _mensajeError.value = null
    }

    fun mostrarError(msg: String) {
        _mensajeError.value = msg
    }

}