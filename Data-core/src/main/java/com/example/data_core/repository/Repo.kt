package com.example.data_core.repository


//import com.example.data_core.dao.DAO
//import com.example.data_core.firebase.FirebaseFunService
//import com.example.data_core.model.Post
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.launch
//
//class Repo(private val dao: DAO) {
//
//    fun getPosts(): Flow<List<Post>> = dao.getPosts()
//
//    fun syncPostsFromFirebase() {
//        FirebaseFunService.getAllPosts { list ->
//            CoroutineScope(Dispatchers.IO).launch {
//                list.forEach { dao.insert(it) }
////            }
//        }
//    }
//
//    fun addPost(post: Post) {
//        FirebaseFunService.addPost(post) { success ->
//            if (success) {
//                CoroutineScope(Dispatchers.IO).launch {
//                    dao.insert(post)
//                }
//            }
//        }
//    }
//}
