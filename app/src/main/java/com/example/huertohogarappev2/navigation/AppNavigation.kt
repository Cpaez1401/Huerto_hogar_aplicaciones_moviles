import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.huertohogarappev2.ui.components.BottomBar
import com.example.huertohogarappev2.ui.screen.CarritoScreen
import com.example.huertohogarappev2.ui.screen.LoginScreen
import com.example.huertohogarappev2.ui.screen.RegistroScreen
import com.example.huertohogarappev2.ui.screens.HomeScreen
import com.example.huertohogarappev2.ui.screens.ProductosScreen
import com.example.huertohogarappev2.viewmodel.LoginViewModel
import com.example.huertohogarappev2.viewmodel.ProductoViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val route = navController.currentBackStackEntryAsState().value?.destination?.route

            // Mostrar barra solo en estas pantallas
            if (route in listOf("home", "productos", "carrito")) {
                BottomBar(navController)
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(padding)
        ) {

            composable("login") {

                val viewModel: LoginViewModel = viewModel()

                LoginScreen(
                    viewModel = viewModel,
                    onLogin = { navController.navigate("home") },
                    onGoToRegister = { navController.navigate("registro") }
                )
            }

            composable("registro") {
                RegistroScreen(
                    onRegistrar = { navController.navigate("home") },
                    onIrLogin = { navController.navigate("login") }
                )
            }

            composable("home") {
                HomeScreen(navController)
            }

            composable("productos") {
                ProductosScreen(navController = navController)
            }

            composable("carrito") {
                CarritoScreen(navController)
            }
        }
    }
}
