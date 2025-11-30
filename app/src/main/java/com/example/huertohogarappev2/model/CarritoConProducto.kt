package com.example.huertohogarappev2.model

import androidx.room.Embedded
import androidx.room.Relation

data class CarritoConProducto(
    @Embedded val carrito: Carrito,
    @Relation(
        parentColumn = "productoId",
        entityColumn = "id"
    )
    val producto: Producto
)