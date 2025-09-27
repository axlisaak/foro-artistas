package com.example.act_clase_pf.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.act_clase_pf.login.LoginScreen
import com.example.ft_foro.ForoScreen
import com.example.ft_spalsh.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController) }

        composable("login") { LoginScreen(navController) }

        composable("foro") { ForoScreen() }

    }
}