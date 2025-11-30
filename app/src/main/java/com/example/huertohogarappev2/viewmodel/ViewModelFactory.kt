package com.example.huertohogarappev2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.huertohogarappev2.data.database.AppDatabase

class ViewModelFactory(
    private val database: AppDatabase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(database.usuarioDao()) as T

            modelClass.isAssignableFrom(RegistroViewModel::class.java) ->
                RegistroViewModel(database.usuarioDao()) as T

            modelClass.isAssignableFrom(CarritoViewModel::class.java) ->
                CarritoViewModel(database.carritoDao()) as T

            modelClass.isAssignableFrom(ProductoViewModel::class.java) ->
                ProductoViewModel(database.productoDao()) as T

            modelClass.isAssignableFrom(PerfilViewModel::class.java) ->
                PerfilViewModel(database.usuarioDao()) as T


            else -> throw IllegalArgumentException("ViewModel desconocido: ${modelClass.name}")
        }
    }
}
