package com.example.act_clase_pf.foro

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ForoScreen(foroViewModel: ForoViewModel = viewModel()) {
    val posts = foroViewModel.postsFiltrados.collectAsState()
    val categorias = listOf("Todos") + foroViewModel.categorias
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("Todos") }
    Column(modifier = Modifier.fillMaxSize()) {

        //expandir para mostra las categorias
        Box(modifier = Modifier.padding(16.dp)) {
            Button(onClick = { expanded = true }) {
                Text(selectedCategory)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ){
                categorias.forEach { categoria ->
                    DropdownMenuItem(
                        text = { Text(categoria) },
                        onClick = {
                            selectedCategory = categoria
                            expanded = false
                            foroViewModel.filtrarPorCategoria(categoria) //aplica el filtro
                        }
                    )
                }
            }
        }
        //lista ya filtrada
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(posts.value) { post ->
                PostItem(post) }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = post.titulo,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Categoría: ${post.categoria}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = post.imageRes),
                contentDescription = post.titulo,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Autor: ${post.autor}",
                fontSize = 14.sp
            )
        }
    }
}
