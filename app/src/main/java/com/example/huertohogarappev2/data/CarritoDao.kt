// /app/src/main/java/com/example/huertohogarappev2/data/CarritoDao.kt
package com.example.huertohogarappev2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.huertohogarappev2.model.Carrito
import com.example.huertohogarappev2.model.CarritoConProducto
import kotlinx.coroutines.flow.Flow

@Dao
interface CarritoDao {
    @Insert
    suspend fun insertar(carrito: Carrito)

    @Update
    suspend fun actualizar(carrito: Carrito)

    @Delete
    suspend fun eliminar(carrito: Carrito)

    @Query("SELECT * FROM carrito")
    suspend fun obtenerTodos(): List<Carrito>

    @Query("SELECT * FROM carrito WHERE usuarioId = :usuarioId")
    suspend fun obtenerPorUsuario(usuarioId: Int): List<Carrito>

    @Query("SELECT * FROM carrito WHERE usuarioId = :usuarioId AND productoId = :productoId")
    suspend fun obtenerItemCarrito(usuarioId: Int, productoId: Int): Carrito?

    @Query("DELETE FROM carrito WHERE usuarioId = :usuarioId")
    suspend fun limpiarCarrito(usuarioId: Int)

    @Query("DELETE FROM carrito WHERE usuarioId = :usuarioId AND productoId = :productoId")
    suspend fun eliminarDelCarrito(usuarioId: Int, productoId: Int)

    /**
     * Nueva función para obtener los ítems del carrito con la información completa del producto.
     * Usa @Transaction y devuelve un Flow para reactividad.
     */
    @Transaction
    @Query("SELECT * FROM carrito WHERE usuarioId = :usuarioId")
    fun obtenerCarritoConProductos(usuarioId: Int): Flow<List<CarritoConProducto>>

}