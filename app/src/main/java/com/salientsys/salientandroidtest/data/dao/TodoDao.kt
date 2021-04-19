package com.salientsys.salientandroidtest.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salientsys.salientandroidtest.data.entity.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getTodos(): LiveData<List<Todo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<Todo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoById(todo: Int): LiveData<Todo>
}