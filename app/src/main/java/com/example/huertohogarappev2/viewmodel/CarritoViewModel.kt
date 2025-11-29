package com.example.huertohogarappev2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogarappev2.data.HuertoHogarDatabase
import com.example.huertohogarappev2.model.Carrito
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

open class CarritoViewModel(application: Application) : AndroidViewModel(application) {

    private val db = HuertoHogarDatabase.getDatabase(application)
    private val carritoDao = db.carritoDao()

    val _carrito = MutableStateFlow<List<Carrito>>(emptyList())
    val carrito = _carrito

    private val usuarioId = 1 // temporal

    init {
        cargarCarrito()
    }

    fun cargarCarrito() {
        viewModelScope.launch {
            _carrito.value = carritoDao.obtenerPorUsuario(usuarioId)
        }
    }

    fun agregarAlCarrito(productoId: Int) {
        viewModelScope.launch {

            val existente = carritoDao.obtenerItemCarrito(usuarioId, productoId)

            if (existente != null) {
                // Incrementar cantidad
                val actualizado = existente.copy(
                    cantidad = existente.cantidad + 1
                )
                carritoDao.actualizar(actualizado)
            } else {
                // Insertar nuevo item
                carritoDao.insertar(
                    Carrito(
                        usuarioId = usuarioId,
                        productoId = productoId,
                        cantidad = 1
                    )
                )
            }

            cargarCarrito()
        }
    }

    fun eliminarDelCarrito(productoId: Int) {
        viewModelScope.launch {
            carritoDao.eliminarDelCarrito(usuarioId, productoId)
            cargarCarrito()
        }
    }

    fun limpiarCarrito() {
        viewModelScope.launch {
            carritoDao.limpiarCarrito(usuarioId)
            cargarCarrito()
        }
    }
}

