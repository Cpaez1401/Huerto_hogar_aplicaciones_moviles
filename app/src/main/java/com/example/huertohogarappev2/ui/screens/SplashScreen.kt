package com.example.huertohogarappev2.ui.screens

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.huertohogarappev2.R
import kotlinx.coroutines.delay
import com.example.huertohogarappev2.ui.theme.VerdeEsmeralda



@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate("login"){
            popUpTo("splash"){ inclusive = true}
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(VerdeEsmeralda)
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Huerto Hogar",
            modifier = Modifier.size(250.dp)
        )
    }
}


