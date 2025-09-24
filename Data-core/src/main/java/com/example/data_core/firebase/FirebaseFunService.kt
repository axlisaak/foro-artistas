//package com.example.data_core.firebase
//
//import com.example.data_core.model.Post
//import com.google.firebase.firestore.FirebaseFirestore
//
//object FirebaseFunService {
//    private val db = FirebaseFirestore.getInstance()
//
//    fun addPost(post: Post, onResult: (Boolean) -> Unit) {
//        db.collection("posts")
//            .add(post.toMap())
//            .addOnSuccessListener { onResult(true) }
//            .addOnFailureListener { onResult(false) }
//    }
//
//    fun getAllPosts(onResult: (List<Post>) -> Unit) {
//        db.collection("posts")
//            .get()
//            .addOnSuccessListener { result ->
//                val list = result.documents.mapNotNull { doc ->
//                    val data = doc.data ?: return@mapNotNull null
//                    Post(
//                        title = data["title"] as? String ?: "",
//                        description = data["description"] as? String ?: "",
//                        imageUrl = data["imageUrl"] as? String ?: "",
//                        author = data["author"] as? String ?: ""
//                    )
//                }
//                onResult(list)
//            }
//    }
//}
