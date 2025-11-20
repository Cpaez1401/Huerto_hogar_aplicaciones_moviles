package com.example.huertohogarappev2.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.huertohogarappev2.model.Carrito
import com.example.huertohogarappev2.model.DetallePedido
import com.example.huertohogarappev2.model.Pedido
import com.example.huertohogarappev2.model.Producto
import com.example.huertohogarappev2.model.Usuario
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        Usuario::class,
        Producto::class,
        Pedido::class,
        DetallePedido::class,
        Carrito::class
    ],
    version = 1
)
    abstract class HuertoHogarDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun productoDao(): ProductoDao
    abstract fun pedidoDao(): PedidoDao
    abstract fun detallePedidoDao(): DetallePedidoDao
    abstract fun carritoDao(): CarritoDao

    companion object {
        private var database: HuertoHogarDatabase? = null

        fun getDatabase(context: Context): HuertoHogarDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    HuertoHogarDatabase::class.java,
                    "huertohogar.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            insertarDatosPorDefecto(database!!)
                        }
                    }
                })
                    .setJournalMode(JournalMode.TRUNCATE)
                    .build()
            }
            return database!!
        }

        private suspend fun insertarDatosPorDefecto(db: HuertoHogarDatabase) {
            val usuarioDao = db.usuarioDao()

            val usuarios = listOf(
                Usuario(
                    nombre = "Juan Pérez", 
                    correo = "juan@huertohogar.cl", 
                    contrasena = "1234",
                    direccion = "Av. Siempre Viva 123, Santiago",
                    telefono = "+569 1234 5678",
                    activo = true
                ),
                Usuario(
                    nombre = "María García", 
                    correo = "maria@huertohogar.cl", 
                    contrasena = "1234",
                    direccion = "Calle Falsa 456, Viña del Mar",
                    telefono = "+569 8765 4321",
                    activo = true
                ),
            )

            usuarios.forEach { usuarioDao.insertar(it) }

            val productoDao = db.productoDao()

            val productos = listOf(
                Producto(
                    nombre = "Manzanas Fuji",
                    descripcion = "Manzanas crujientes y dulces del Valle del Maule",
                    precio = 1200,
                    imagen = "...",
                    stock = 150,
                    categoria = "Frutas Frescas",
                    unidad = "kilo",
                    activo = true
                ),
                Producto(
                    nombre = "Naranjas Valencia",
                    descripcion = "Naranjas jugosas ricas en vitamina C",
                    precio = 1000,
                    imagen = "...",
                    stock = 200,
                    categoria = "Frutas Frescas", 
                    unidad = "kilo",
                    activo = true
                ),
                Producto(
                    nombre = "Zanahorias Orgánicas",
                    descripcion = "Zanahorias cultivadas sin pesticidas en O'Higgins",
                    precio = 900,
                    imagen = "...",
                    stock = 100,
                    categoria = "Verduras Orgánicas",
                    unidad = "kilo",
                    activo = true
                ),
                Producto(
                    nombre = "Miel Orgánica", 
                    descripcion = "Miel pura de apicultores locales",
                    precio = 5000,
                    imagen = "....",
                    stock = 50,
                    categoria = "Productos Orgánicos",
                    unidad = "frasco",
                    activo = true
                ),
                Producto(
                    nombre = "Espinacas Frescas",
                    descripcion = "Espinacas frescas y nutritivas",
                    precio = 700,
                    imagen = "...",
                    stock = 80,
                    categoria = "Verduras Orgánicas",
                    unidad = "bolsa",
                    activo = true
                )
            )

            productos.forEach { productoDao.insertar(it) }
        }
    }
}
