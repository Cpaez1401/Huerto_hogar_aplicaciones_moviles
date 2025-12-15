package com.example.huertohogarappv2.data.model
import com.example.huertohogarappev2.model.Producto
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.Int

class ProductoTest {

    @Test
    fun producto_conStockCero_tieneStockCero() {
        val producto = Producto(
            id= 1,
        nombre= "lechuga",
        precio= 1000,
        descripcion= "Fresca y sana",
        imagen= null,
        stock= 0,
        categoria= "Verduras",
        unidad= "Unidad",
        activo= true
        )

        assertEquals(0, producto.stock)
    }
}