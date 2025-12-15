package com.example.huertohogarappev2.util

fun calcularTotalCarrito(items: List<Pair<Int, Int>>): Int {
    // Cada item tiene: precio y cantidad
    return items.sumOf { it.first * it.second }
}