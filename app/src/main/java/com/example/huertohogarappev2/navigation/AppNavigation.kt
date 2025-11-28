import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.huertohogarappev2.ui.screen.LoginScreen
import com.example.huertohogarappev2.ui.screen.RegistroScreen
import com.example.huertohogarappev2.ui.screens.HomeScreen
import com.example.huertohogarappev2.viewmodel.LoginViewModel

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
            RegistroScreen (
            onRegistrar = { navController.navigate("home") },
            onIrLogin = { navController.navigate("login") }
            )
        }

        composable("home"){
            HomeScreen(navController)
        }

    }


}