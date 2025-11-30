package com.example.huertohogarappev2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.huertohogarappev2.data.ProductoDao
import com.example.huertohogarappev2.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow

class ProductoViewModel(productoDao: ProductoDao) : ViewModel() {

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos = _productos

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        val productos = listOf(
            Producto(
                nombre = "Manzanas Fuji",
                descripcion = "Manzanas crujientes y dulces del Valle del Maule",
                precio = 1200,
                imagen = "fuji_red.jpg",
                stock = 150,
                categoria = "Frutas Frescas",
                unidad = "kilo",
                activo = true
            ),
            Producto(
                nombre = "Naranjas Valencia",
                descripcion = "Naranjas jugosas ricas en vitamina C",
                precio = 1000,
                imagen = "naranjas_valencia.webp",
                stock = 200,
                categoria = "Frutas Frescas",
                unidad = "kilo",
                activo = true
            ),
            Producto(
                nombre = "Zanahorias Orgánicas",
                descripcion = "Zanahorias cultivadas sin pesticidas en O'Higgins",
                precio = 900,
                imagen = "zanahorias_organicas.jpg",
                stock = 100,
                categoria = "Verduras Orgánicas",
                unidad = "kilo",
                activo = true
            ),
            Producto(
                nombre = "Miel Orgánica",
                descripcion = "Miel pura de apicultores locales",
                precio = 5000,
                imagen = "miel.jpg",
                stock = 50,
                categoria = "Productos Orgánicos",
                unidad = "frasco",
                activo = true
            ),
            Producto(
                nombre = "Espinacas Frescas",
                descripcion = "Espinacas frescas y nutritivas",
                precio = 700,
                imagen = "espinacas_organicas.jpg",
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

