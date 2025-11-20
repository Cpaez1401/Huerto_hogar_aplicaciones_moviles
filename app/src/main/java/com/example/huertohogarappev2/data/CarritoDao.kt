package com.example.huertohogarappev2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.huertohogarappev2.model.Carrito

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

}
