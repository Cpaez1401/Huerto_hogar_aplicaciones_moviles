package com.example.huertohogarappev2.model

import androidx.room.Entity

@Entity(tableName = "carrito",
    primaryKeys = ["usuarioId", "productoId"])
data class Carrito(
    val usuarioId: Int,
    val productoId: Int,
    val cantidad: Int = 1
)
