package com.example.huertohogarappev2.model

import androidx.room.Entity

@Entity(tableName = "detallepedido",
    primaryKeys = ["pedidoId", "productoId"])
data class DetallePedido(
    val pedidoId: Int,
    val productoId: Int,
    val cantidad: Int
)
