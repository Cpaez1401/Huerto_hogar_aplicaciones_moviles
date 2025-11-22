package com.example.huertohogarappev2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarritoScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDE7F6)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            Text(
                text = "Carrito",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5E35B1)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Aquí se mostrarán productos agregados al carrito",
                color = Color.DarkGray
            )
        }
    }
}
