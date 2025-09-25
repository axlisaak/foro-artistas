package com.example.act_clase_pf.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginUsuarioScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login Usuario")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                //  Navegar al foro después del login
                navController.navigate("foro")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar al Foro")
        }
    }
}
