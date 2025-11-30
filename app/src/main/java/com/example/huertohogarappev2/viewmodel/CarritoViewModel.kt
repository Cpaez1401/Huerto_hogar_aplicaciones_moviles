package com.example.huertohogarappev2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import com.example.huertohogarappev2.data.CarritoDao
import com.example.huertohogarappev2.data.HuertoHogarDatabase
import com.example.huertohogarappev2.model.Carrito
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CarritoViewModel(
    private val carritoDao: CarritoDao
) : ViewModel() {

    private val _carrito = MutableStateFlow<List<Carrito>>(emptyList())
    val carrito = _carrito.asStateFlow()

    private val usuarioId = 1 // Temporal hasta que Login entregue el usuario real

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

            val itemExistente = carritoDao.obtenerItemCarrito(usuarioId, productoId)

            if (itemExistente != null) {
                val actualizado = itemExistente.copy(cantidad = itemExistente.cantidad + 1)
                carritoDao.actualizar(actualizado)
            } else {
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
