package com.salientsys.salientandroidtest.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salientsys.salientandroidtest.data.entity.Comment

@Dao
interface CommentDao {
    @Query("SELECT * FROM comment")
    fun getComments(): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comments: List<Comment>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: Comment)

    @Query("SELECT * FROM comment WHERE id = :id")
    fun getCommentById(id: Int): LiveData<Comment>
}