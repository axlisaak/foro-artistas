package com.example.data_core.dao


import androidx.room.*
import com.example.data_core.model.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: Post)

    @Query("SELECT * FROM posts")
    fun getPosts(): Flow<List<Post>>

    @Delete
    suspend fun delete(post: Post)
}
