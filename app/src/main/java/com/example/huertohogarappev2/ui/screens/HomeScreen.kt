package com.example.huertohogarappev2.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProductoClick: (Int) -> Unit,
    onCarritoClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Huerto Hogar") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCarritoClick) {
                Text("🛒")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(5) { index ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onClick = { onProductoClick(index) }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Producto $index", style = MaterialTheme.typography.titleMedium)
                        Text("Descripción del producto…")
                    }
                }
            }
        }
    }
}