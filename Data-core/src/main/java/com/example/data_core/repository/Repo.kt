package com.example.data_core.repository


import com.example.data_core.dao.DAO
import com.example.data_core.firebase.FirebaseFunService
import com.example.data_core.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class Repo(private val dao: DAO) {

    private val firebaseService = FirebaseFunService

    fun getPosts(): Flow<List<Post>> = dao.getPosts()

    suspend fun syncPostsFromFirebase() {
        withContext(Dispatchers.IO) {
            val postsFromFirebase = firebaseService.getAllPosts()
            postsFromFirebase.forEach { dao.insert(it) }
        }
    }

    suspend fun addPost(post: Post) {
        withContext(Dispatchers.IO) {
            firebaseService.addPost(post)
            syncPostsFromFirebase()
        }
    }
}