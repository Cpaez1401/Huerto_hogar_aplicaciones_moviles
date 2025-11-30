// /app/src/main/java/com/example/huertohogarappev2/model/CarritoConProducto.kt
package com.example.huertohogarappev2.model

import androidx.room.Embedded
import androidx.room.Relation

data class CarritoConProducto(
    @Embedded val carrito: Carrito,
    @Relation(
        parentColumn = "productoId", // Columna en la entidad Carrito
        entityColumn = "id"          // Columna en la entidad Producto
    )
    val producto: Producto
)