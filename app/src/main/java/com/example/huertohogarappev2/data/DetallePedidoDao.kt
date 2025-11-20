package com.example.huertohogarappev2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.huertohogarappev2.model.DetallePedido

@Dao
interface DetallePedidoDao {
    @Insert
    suspend fun insertar(detalle: DetallePedido)

    @Update
    suspend fun actualizar(detalle: DetallePedido)

    @Delete
    suspend fun eliminar(detalle: DetallePedido)

    @Query("SELECT * FROM detallepedido")
    suspend fun obtenerTodos(): List<DetallePedido>

    @Query("SELECT * FROM detallepedido WHERE pedidoId = :pedidoId")
    suspend fun obtenerPorPedido(pedidoId: Int): List<DetallePedido>

    @Query("SELECT * FROM detallepedido WHERE productoId = :productoId")
    suspend fun obtenerPorProducto(productoId: Int): List<DetallePedido>
}
