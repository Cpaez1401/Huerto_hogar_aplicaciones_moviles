package com.example.huertohogarappev2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.huertohogarappev2.data.CarritoDao
import com.example.huertohogarappev2.data.ProductoDao
import com.example.huertohogarappev2.data.UsuarioDao
import com.example.huertohogarappev2.model.Carrito
import com.example.huertohogarappev2.model.Producto
import com.example.huertohogarappev2.model.Usuario


@Database(
    entities = [
        Usuario::class,
        Carrito::class,
        Producto::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun carritoDao(): CarritoDao

    abstract fun productoDao(): ProductoDao



    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "huerto_hogar_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}
