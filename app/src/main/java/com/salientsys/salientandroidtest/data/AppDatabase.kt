package com.salientsys.salientandroidtest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salientsys.salientandroidtest.data.dao.PostDao
import com.salientsys.salientandroidtest.data.dao.UserDao
import com.salientsys.salientandroidtest.data.entity.Post
import com.salientsys.salientandroidtest.data.entity.User

@Database(
    entities = [
        User::class,
        Post::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
}
