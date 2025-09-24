package com.example.act_clase_pf.pantallaSplah



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act_clase_pf.R

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Tu logo
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // <-- cámbialo por tu logo
                contentDescription = "Logo",
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Botón Usuario
            Button(
                onClick = { navController.navigate("login_usuario") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ingresar como Usuario")
            }

            // Botón Artista
            Button(
                onClick = { navController.navigate("login_artista") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ingresar como Artista")
            }

            // Botón Administrador
            Button(
                onClick = { navController.navigate("login_admin") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ingresar como Administrador")
            }
        }
    }
}
