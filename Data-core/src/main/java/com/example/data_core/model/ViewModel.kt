//package com.example.data_core.model
//
//
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//
//@Entity(tableName = "posts")
//data class Post(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val title: String = "",
//    val description: String = "",
//    val imageUrl: String = "",
//    val author: String = ""
//) {
//    fun toMap(): Map<String, Any> = mapOf(
//        "title" to title,
//        "description" to description,
//        "imageUrl" to imageUrl,
//        "author" to author
//    )
//}
