package com.example.act_clase_pf.foro

import androidx.lifecycle.ViewModel
import com.example.act_clase_pf.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Post(
    val titulo: String,
    val autor: String,
    val imageRes: Int
)
class ForoViewModel : ViewModel() {
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
        _posts.value = listOf(
            Post("Dibujo test 1", "papu", R.drawable.resource_for),
            Post("Dibujo test 2", "tilin", R.drawable.mori_september_wallpaper_no_glasses),
            Post("Dibujo test3", "el pepe", R.drawable.thankyou_guyssssss_ehe)
        )
    }
}
