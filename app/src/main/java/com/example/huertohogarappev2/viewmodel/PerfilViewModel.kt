package com.example.huertohogarappev2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogarappev2.data.UsuarioDao
import com.example.huertohogarappev2.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(
    private val usuarioDao: UsuarioDao
) : ViewModel() {

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    // Temporal, hasta que Login lo entregue
    private val usuarioId = 1

    init {
        cargarUsuario()
    }

    fun cargarUsuario() {
        viewModelScope.launch {
            _usuario.value = usuarioDao.obtenerPorId(usuarioId)
        }
    }

    fun actualizarPerfil(
        nombre: String,
        direccion: String,
        telefono: String
    ) {
        viewModelScope.launch {
            val current = _usuario.value ?: return@launch

            val actualizado = current.copy(
                nombre = nombre,
                direccion = direccion,
                telefono = telefono
            )

            usuarioDao.actualizar(actualizado)
            _usuario.value = actualizado
        }
    }
}
