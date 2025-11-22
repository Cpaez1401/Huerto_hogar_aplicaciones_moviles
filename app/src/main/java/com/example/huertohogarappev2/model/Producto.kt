package com.example.huertohogarappev2.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Producto")
data class Producto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val precio: Int,
    val descripcion: String,
    val imagen: String?,
    val stock: Int,
    val categoria: String,
    val unidad: String,
    val activo: Boolean
)
