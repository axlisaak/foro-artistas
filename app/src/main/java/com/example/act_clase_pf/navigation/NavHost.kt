package com.example.act_clase_pf.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.act_clase_pf.login.LoginAdminScreen
import com.example.act_clase_pf.login.LoginArtistaScreen
import com.example.act_clase_pf.login.LoginUsuarioScreen
import com.example.act_clase_pf.pantallaSplah.SplashScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ){

        composable("splash"){SplashScreen(navController)}
        composable("login_usuario"){ LoginUsuarioScreen(navController)}
        composable("login_artista"){ LoginArtistaScreen(navController)}
        composable("login_admin"){ LoginAdminScreen(navController)}

    }
}

