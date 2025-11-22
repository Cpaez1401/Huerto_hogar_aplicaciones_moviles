package com.example.huertohogarappev2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.huertohogarappev2.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow

class CarritoViewModel : ViewModel() {

    private val _carrito = MutableStateFlow<List<Producto>>(emptyList())
    val carrito = _carrito

    fun agregar(producto: Producto) {
        _carrito.value = _carrito.value + producto
    }

    fun eliminar(id: Int) {
        _carrito.value = _carrito.value.filter { it.id != id }
    }

    fun vaciar() {
        _carrito.value = emptyList()
    }

    fun precioTotal(): Int {
        return _carrito.value.sumOf { it.precio }
    }
}
