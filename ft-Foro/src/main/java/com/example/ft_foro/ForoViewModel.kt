package com.example.ft_foro


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.ft_foro.R


data class Post(
    val titulo:String,
    val autor:String,
    val imageRes:Int,
    val categoria:String
)

class ForoViewModel : ViewModel() {

    // Lista de las categorias
    val categorias = listOf("Zenless Zone Zero", "HoloEN", "HoloID","HoloJP")

    // Todos los posts
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    // filtrado de los post por categorias
    private val _postsFiltrados = MutableStateFlow<List<Post>>(emptyList())
    val postsFiltrados: StateFlow<List<Post>> = _postsFiltrados

    init{
        _posts.value = listOf(
            Post("Dibujo test 1", "papu", R.drawable.resource_for, "Zenless Zone Zero"),
            Post("Dibujo test 2", "tilin", R.drawable.mori_september_wallpaper_no_glasses, "HoloEN"),
            Post("Dibujo test 3", "el pepe", R.drawable.thankyou_guyssssss_ehe, "HoloID")
        )
        // Inicialmente mostramos todos
        _postsFiltrados.value = _posts.value
    }

    // Función para filtrar por categoría
    fun filtrarPorCategoria(categoria: String) {
        if (categoria == "Todos") {
            _postsFiltrados.value = _posts.value
        }else {
            _postsFiltrados.value = _posts.value.filter { it.categoria == categoria }
        }
        fun filtrarPorTexto(texto: String) {
            val lista = _posts.value.filter { post ->
                post.titulo.contains(texto, ignoreCase = true) ||
                        post.autor.contains(texto, ignoreCase = true)
            }
            _postsFiltrados.value = lista
        }

    }
    fun filtrarPorTexto(texto: String) {
        val lista = _posts.value.filter { post ->
            post.titulo.contains(texto, ignoreCase = true) ||
                    post.autor.contains(texto, ignoreCase = true)
        }
        _postsFiltrados.value = lista
    }

}


