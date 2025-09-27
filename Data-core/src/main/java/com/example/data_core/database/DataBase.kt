package com.example.data_core.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data_core.dao.DAO
import com.example.data_core.model.Post

@Database(
    entities = [Post::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {
    abstract fun dao(): DAO
}
