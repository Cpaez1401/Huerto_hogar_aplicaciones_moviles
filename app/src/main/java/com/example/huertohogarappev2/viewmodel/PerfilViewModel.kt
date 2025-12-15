package com.example.huertohogarappev2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogarappev2.data.UsuarioDao
import com.example.huertohogarappev2.model.Usuario
import kotlinx.coroutines.launch

class PerfilViewModel(private val usuarioDao: UsuarioDao) : ViewModel() {

    var usuario by mutableStateOf<Usuario?>(null)
        private set

    fun cargarUsuario(id: Int) {
        viewModelScope.launch {
            usuario = usuarioDao.obtenerPorId(id)
        }
    }

    fun actualizarPerfil(
        nombre: String,
        direccion: String,
        telefono: String
    ) {
        viewModelScope.launch {
            val current = usuario ?: return@launch

            val actualizado = current.copy(
                nombre = nombre,
                direccion = direccion,
                telefono = telefono
            )

            usuarioDao.actualizar(actualizado)
            usuario = actualizado
        }
    }
}
