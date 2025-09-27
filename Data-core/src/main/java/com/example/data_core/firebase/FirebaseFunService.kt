package com.example.data_core.firebase

import com.example.data_core.model.Post
import com.example.data_core.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirebaseFunService {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()



    fun getCurrentUserId(): String? = auth.currentUser?.uid

    suspend fun registerUser(email: String, pass: String, displayName: String, role: String) {

        val authResult = auth.createUserWithEmailAndPassword(email, pass).await()
        val firebaseUser = authResult.user ?: throw Exception("Error al crear el usuario.")


        val user = User(
            uid = firebaseUser.uid,
            email = email,
            displayName = displayName,
            role = role
        )
        db.collection("users").document(firebaseUser.uid).set(user).await()
    }

    suspend fun signInWithEmail(email: String, pass: String): String {
        val result = auth.signInWithEmailAndPassword(email, pass).await()
        return result.user?.uid ?: throw Exception("Error en el inicio de sesión.")
    }

    suspend fun signInWithGoogle(idToken: String, displayName: String, email: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        val result = auth.signInWithCredential(credential).await()
        val firebaseUser = result.user ?: throw Exception("Error con la autenticación de Google.")

        val userDoc = db.collection("users").document(firebaseUser.uid).get().await()
        if (!userDoc.exists()) {
            val newUser = User(
                uid = firebaseUser.uid,
                email = email,
                displayName = displayName,
                role = "USER"
            )
            db.collection("users").document(firebaseUser.uid).set(newUser).await()
        }
    }

    suspend fun getUserProfile(uid: String): User? {
        return db.collection("users").document(uid).get().await().toObject(User::class.java)
    }

    fun logout() {
        auth.signOut()
    }



    suspend fun addPost(post: Post) {
        val authorId = getCurrentUserId() ?: "unknown"
        val postWithAuthor = post.copy(author = authorId)
        db.collection("posts").add(postWithAuthor.toMap()).await()
    }

    suspend fun getAllPosts(): List<Post> {
        val result = db.collection("posts").get().await()
        return result.documents.mapNotNull { doc ->
            doc.toObject(Post::class.java)
        }
    }
}