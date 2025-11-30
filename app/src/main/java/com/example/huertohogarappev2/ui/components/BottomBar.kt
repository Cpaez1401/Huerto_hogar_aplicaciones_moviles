package com.example.huertohogarappev2.ui.components

import android.R.attr.label
import android.R.attr.onClick
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Store
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavController) {

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = Color(0xFF4CAF50),
        contentColor = Color.White,
        tonalElevation = 4.dp,
        modifier = androidx.compose.ui.Modifier.height(70.dp)
    ) {

        NavigationBarItem(
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Default.Home, "Home") },
            label = { Text("Inicio") }
        )

        NavigationBarItem(
            selected = currentRoute == "productos",
            onClick = { navController.navigate("productos") },
            icon = { Icon(Icons.Filled.Store, contentDescription = "Productos") },
            label = { Text("Productos") }
        )

        NavigationBarItem(
            selected = currentRoute == "carrito",
            onClick = { navController.navigate("carrito") },
            icon = { Icon(Icons.Default.ShoppingCart, "Carrito") },
            label = { Text("Carrito") }
        )

        NavigationBarItem(
            selected = currentRoute == "perfil",
            onClick = { navController.navigate("perfil") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
            label = { Text("Perfil") }
        )

    }
}
