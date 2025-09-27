package com.example.act_clase_pf.login

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.example.act_clase_pf.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {

    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    var isLoginMode by remember { mutableStateOf(true) }
    val email by loginViewModel.email.collectAsStateWithLifecycle()
    val password by loginViewModel.password.collectAsStateWithLifecycle()
    val displayName by loginViewModel.displayName.collectAsStateWithLifecycle()
    val selectedRole by loginViewModel.selectedRole.collectAsStateWithLifecycle()


    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                loginViewModel.onGoogleSignIn(
                    idToken = account.idToken!!,
                    displayName = account.displayName ?: "",
                    email = account.email ?: ""
                )
            } catch (e: ApiException) {
                Toast.makeText(context, "Falló el inicio de sesión con Google.", Toast.LENGTH_SHORT).show()
            }
        }
    )

    // Manejar el estado de la UI (éxito, error, carga)
    LaunchedEffect(key1 = uiState) {
        when (val state = uiState) {
            is LoginUiState.Success -> {
                Toast.makeText(context, "Bienvenido ${state.user.displayName}", Toast.LENGTH_SHORT).show()
                navController.navigate("foro") {
                    popUpTo("login") { inclusive = true }
                }
            }
            is LoginUiState.Error -> {
                Toast.makeText(context, state.message, Toast.LENGTH_LONG).show()
                loginViewModel.resetState()
            }
            else -> {}
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState is LoginUiState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (isLoginMode) "Iniciar Sesión" else "Crear Cuenta",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(Modifier.height(24.dp))

                if (!isLoginMode) {
                    OutlinedTextField(
                        value = displayName,
                        onValueChange = { loginViewModel.displayName.value = it },
                        label = { Text("Nombre Completo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(8.dp))
                }

                OutlinedTextField(
                    value = email,
                    onValueChange = { loginViewModel.email.value = it },
                    label = { Text("Correo Electrónico") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { loginViewModel.password.value = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))

                if (!isLoginMode) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedRole == "USER",
                            onClick = { loginViewModel.selectedRole.value = "USER" }
                        )
                        Text("Usuario")
                        Spacer(Modifier.width(16.dp))
                        RadioButton(
                            selected = selectedRole == "ARTIST",
                            onClick = { loginViewModel.selectedRole.value = "ARTIST" }
                        )
                        Text("Artista")
                    }
                    Spacer(Modifier.height(16.dp))
                }

                Button(
                    onClick = {
                        if (isLoginMode) loginViewModel.onLoginClicked() else loginViewModel.onRegisterClicked()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (isLoginMode) "Entrar" else "Registrar")
                }

                TextButton(onClick = { isLoginMode = !isLoginMode }) {
                    Text(if (isLoginMode) "¿No tienes cuenta? Regístrate" else "Ya tengo una cuenta")
                }

                Divider(modifier = Modifier.padding(vertical = 16.dp))

                Button(
                    onClick = {
                        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(context.getString(R.string.default_web_client_id))
                            .requestEmail()
                            .build()
                        val googleSignInClient = GoogleSignIn.getClient(context, gso)
                        googleSignInLauncher.launch(googleSignInClient.signInIntent)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Iniciar Sesión con Google")
                }
            }
        }
    }
}