package com.example.huertohogarappev2.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun TituloText(texto: String) {
    Text(
        text = texto,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold
    )
}