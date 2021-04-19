package com.salientsys.salientandroidtest.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salientsys.salientandroidtest.data.entity.Post

@Dao
interface PostDao {
    @Query("SELECT * FROM post")
    fun getPosts(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: Post)

    //@Query("DELETE FROM post WHERE id = :id")
    //fun deletePost(id: Int)

    @Query("SELECT * FROM post WHERE userId = :userId")
    fun getPostByUserId(userId: Int): LiveData<List<Post>>

    @Query("SELECT * FROM post WHERE id = :id")
    fun getPostById(id: Int): LiveData<Post>

}