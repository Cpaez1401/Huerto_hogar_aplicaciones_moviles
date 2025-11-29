import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.huertohogarappev2.ui.screen.CarritoScreen
import com.example.huertohogarappev2.ui.screen.LoginScreen
import com.example.huertohogarappev2.ui.screen.RegistroScreen
import com.example.huertohogarappev2.ui.screens.HomeScreen
import com.example.huertohogarappev2.ui.screens.ProductosScreen
import com.example.huertohogarappev2.viewmodel.CarritoViewModel
import com.example.huertohogarappev2.viewmodel.LoginViewModel
import com.example.huertohogarappev2.viewmodel.ProductoViewModel
import com.example.huertohogarappev2.viewmodel.RegistroViewModel

@Composable
fun AppNavigation(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ){
        composable("login"){

            val viewModel : LoginViewModel = viewModel()

            LoginScreen(
                viewModel = viewModel,
                onLogin = { navController.navigate("home") },
                onGoToRegister = { navController.navigate("registro") }
            )
        }

        composable("registro"){

            val viewModel: RegistroViewModel = viewModel()

            RegistroScreen (
                viewModel = viewModel,
                onRegistrar = { navController.navigate("home") },
                onIrLogin = { navController.navigate("login") }
            )
        }

        composable("home"){
            HomeScreen(navController)
        }

        composable("productos") {
            val vm: ProductoViewModel = viewModel()
            ProductosScreen(navController, vm)
        }

        composable("carrito"){
            val carritoVm: CarritoViewModel = viewModel()
            CarritoScreen(navController = navController, carritoViewModel = carritoVm)
        }



    }


}