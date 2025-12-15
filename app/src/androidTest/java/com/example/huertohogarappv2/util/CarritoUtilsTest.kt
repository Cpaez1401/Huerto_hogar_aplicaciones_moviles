package com.example.huertohogarappv2.util

import com.example.huertohogarappev2.util.calcularTotalCarrito
import org.junit.Assert.assertEquals
import org.junit.Test

class CarritoUtilsTest {

    @Test
    fun calcularTotalCarrito_calculaCorrectamente() {
        val productos = listOf(
            Pair(1000, 2),  // 1000 x 2 = 2000
            Pair(500, 1),   // 500 x 1 = 500
            Pair(200, 3)    // 200 x 3 = 600
        )

        val total = calcularTotalCarrito(productos)

        assertEquals(3100, total)
    }
}
