// /app/src/main/java/com/example/huertohogarappev2/viewmodel/CarritoViewModel.kt
package com.example.huertohogarappev2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.huertohogarappev2.data.CarritoDao
import com.example.huertohogarappev2.model.Carrito
import com.example.huertohogarappev2.model.CarritoConProducto
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class CarritoViewModel(
    private val carritoDao: CarritoDao
) : ViewModel() {

    private val usuarioId = 1 // Temporal hasta que Login entregue el usuario real

    /**
     * StateFlow que expone la lista de CarritoConProducto,
     * obtenida de forma reactiva desde el DAO.
     */
    val carritoConProductos: StateFlow<List<CarritoConProducto>> =
        carritoDao.obtenerCarritoConProductos(usuarioId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    // La función cargarCarrito() ya no es necesaria.

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

        }
    }

    fun obtenerCantidad(productoId: Int): Int {
        return carritoConProductos.value
            .firstOrNull { it.producto.id == productoId }
            ?.carrito?.cantidad ?: 0
    }


    fun eliminarDelCarrito(productoId: Int) {
        viewModelScope.launch {
            carritoDao.eliminarDelCarrito(usuarioId, productoId)
            // No es necesario llamar a cargarCarrito()
        }
    }

    fun limpiarCarrito() {
        viewModelScope.launch {
            carritoDao.limpiarCarrito(usuarioId)
            // No es necesario llamar a cargarCarrito()
        }
    }
}


