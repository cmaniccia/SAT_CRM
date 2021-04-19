package com.salientsys.salientandroidtest.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salientsys.salientandroidtest.data.entity.Photo

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo")
    fun getPhotos(): LiveData<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<Photo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Query("SELECT * FROM photo WHERE id = :id")
    fun getPhotoById(id: Int): LiveData<Photo>
}