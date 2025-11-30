package com.example.huertohogarappev2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.huertohogarappev2.data.DetallePedidoDao
import com.example.huertohogarappev2.data.PedidoDao
import com.example.huertohogarappev2.model.Pedido
import com.example.huertohogarappev2.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow

class PedidoViewModel(pedidoDao: PedidoDao, detallePedidoDao: DetallePedidoDao) : ViewModel() {

    private val _pedidos = MutableStateFlow<List<Pedido>>(emptyList())
    val pedidos = _pedidos

    fun crearPedido(usuarioId: Int, productos: List<Producto>) {

        val total = productos.sumOf { it.precio }

        val nuevoPedido = Pedido(
            id = _pedidos.value.size + 1,
            usuarioId = usuarioId,
            fecha = "2024-01-01",
            total = total,
            estado = "pendiente",
            direccionEntrega = "Sin dirección"
        )

        _pedidos.value = _pedidos.value + nuevoPedido
    }
}
