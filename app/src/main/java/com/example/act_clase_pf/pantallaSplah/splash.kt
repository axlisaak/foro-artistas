package com.example.act_clase_pf.pantallaSplah



import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.act_clase_pf.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    //Creamos un estado para controlar la visibilidad de los botones
    //Inicialmente es false  por lo que estarán ocultos
    var showButtons by remember { mutableStateOf(false) }

    //Usamos LaunchedEffect para que se ejecute solo una vez
    LaunchedEffect(key1 = true) {
        //el splash tiene la duracion de 2 segundos
        delay(2000L)
        //con esto el estado cambia mostrnado ahora si los botones
        showButtons = true
    }
    // Box para guardar el contenido
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        // Columna para que el contenido este organizdo
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            //el contenido queda centrado
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            // El logo siempre es visible.
            Image(
                painter = painterResource(id = R.drawable.algo_mal),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .aspectRatio(1f)
            )
            // Un espacio para separar el logo de los botones
            Spacer(modifier = Modifier.height(100.dp))

            //AnimatedVisibility para que los botones aparezcan con una animación suave cuando showButtons sea true
            AnimatedVisibility(
                visible = showButtons,
                enter = fadeIn(animationSpec = androidx.compose.animation.core.tween(durationMillis = 1000))
            ){
                //Ponemos los botones dentro de otra columna para que aparezcan todos juntos
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ){
                    //Boton para seleccionar login de usuario
                    Button(
                        onClick = { navController.navigate("login_usuario") },
                        modifier = Modifier.fillMaxWidth()
                    ){ Text(text = "Ingresar como Usuario")
                    }
                    //Boton para seleccionar login de artista
                    Button(
                        onClick = { navController.navigate("login_artista") },
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(text = "Ingresar como Artista")
                    }
                    //Boton para seleccionar login de admin
                    Button(
                        onClick = { navController.navigate("login_admin") },
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Text(text = "Ingresar como Administrador")
                    }
                }
            }
        } }
}
