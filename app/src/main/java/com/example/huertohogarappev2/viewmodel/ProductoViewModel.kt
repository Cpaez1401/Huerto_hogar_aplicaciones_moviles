package com.example.huertohogarappev2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.huertohogarappev2.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow

class ProductoViewModel : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos = _productos

    init {
        cargarProductos()
    }

    private fun cargarProductos() {
        val productos = listOf(
            Producto(
                nombre = "Manzanas Fuji",
                descripcion = "Manzanas crujientes y dulces del Valle del Maule",
                precio = 1200,
                imagen = "...",
                stock = 150,
                categoria = "Frutas Frescas",
                unidad = "kilo",
                activo = true
            ),
            Producto(
                nombre = "Naranjas Valencia",
                descripcion = "Naranjas jugosas ricas en vitamina C",
                precio = 1000,
                imagen = "...",
                stock = 200,
                categoria = "Frutas Frescas",
                unidad = "kilo",
                activo = true
            ),
            Producto(
                nombre = "Zanahorias Orgánicas",
                descripcion = "Zanahorias cultivadas sin pesticidas en O'Higgins",
                precio = 900,
                imagen = "...",
                stock = 100,
                categoria = "Verduras Orgánicas",
                unidad = "kilo",
                activo = true
            ),
            Producto(
                nombre = "Miel Orgánica",
                descripcion = "Miel pura de apicultores locales",
                precio = 5000,
                imagen = "....",
                stock = 50,
                categoria = "Productos Orgánicos",
                unidad = "frasco",
                activo = true
            ),
            Producto(
                nombre = "Espinacas Frescas",
                descripcion = "Espinacas frescas y nutritivas",
                precio = 700,
                imagen = "...",
                stock = 80,
                categoria = "Verduras Orgánicas",
                unidad = "bolsa",
                activo = true
            )
        )

        _productos.value = productos
    }

    fun obtenerProducto(index: Int): Producto? {
        return _productos.value.getOrNull(index)
    }
}

