package com.example.huertohogarappev2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.huertohogarappev2.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CarritoViewModel : ViewModel() {

    private val _carrito = MutableStateFlow<List<Producto>>(emptyList())
    val carrito = _carrito.asStateFlow()

    fun agregarProducto(producto: Producto) {
        _carrito.value = _carrito.value + producto
    }

    fun eliminarProducto(producto: Producto) {
        _carrito.value = _carrito.value - producto
    }

    fun vaciarCarrito() {
        _carrito.value = emptyList()
    }
}
