
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.huertohogarappev2.data.database.AppDatabase
import com.example.huertohogarappev2.ui.components.BottomBar
import com.example.huertohogarappev2.ui.screen.CarritoScreen
import com.example.huertohogarappev2.ui.screen.LoginScreen
import com.example.huertohogarappev2.ui.screen.RegistroScreen
import com.example.huertohogarappev2.ui.screens.HomeScreen
import com.example.huertohogarappev2.ui.screens.ProductosScreen
import com.example.huertohogarappev2.ui.screens.SplashScreen
import com.example.huertohogarappev2.viewmodel.CarritoViewModel
import com.example.huertohogarappev2.viewmodel.LoginViewModel
import com.example.huertohogarappev2.viewmodel.ProductoViewModel
import com.example.huertohogarappev2.viewmodel.RegistroViewModel
import com.example.huertohogarappev2.viewmodel.ViewModelFactory

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current

    val database = AppDatabase.getDatabase(context)
    val factory = ViewModelFactory(database)


    val loginViewModel: LoginViewModel = viewModel(factory = factory)
    val registroViewModel: RegistroViewModel = viewModel(factory = factory)
    val carritoViewModel: CarritoViewModel = viewModel(factory = factory)
    val productoViewModel: ProductoViewModel = viewModel(factory = factory)

    Scaffold(
        bottomBar = {
            val ruta = navController.currentBackStackEntryAsState().value?.destination?.route
            if (ruta in listOf("home", "productos", "carrito")) {
                BottomBar(navController)
            }
        }
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {

            NavHost(
                navController = navController,
                startDestination = "splash"
            ) {

                composable("splash") {
                    SplashScreen(navController)
                }

                composable("login") {
                    LoginScreen(
                        viewModel = loginViewModel,
                        onLogin = { navController.navigate("home") },
                        onGoToRegister = { navController.navigate("registro") }
                    )
                }

                composable("registro") {
                    RegistroScreen(
                        viewModel = registroViewModel,
                        onRegistrar = { navController.navigate("home") },
                        onIrLogin = { navController.navigate("login") }
                    )
                }

                composable("home") {
                    HomeScreen(
                        navController = navController,
                        productoViewModel = productoViewModel
                    )
                }

                composable("productos") {
                    ProductosScreen(
                        navController = navController,
                        viewModel = productoViewModel
                    )
                }

                composable("carrito") {
                    CarritoScreen(
                        navController = navController,
                        carritoViewModel = carritoViewModel,
                        productoViewModel = productoViewModel
                    )
                }
            }
        }
    }
}
