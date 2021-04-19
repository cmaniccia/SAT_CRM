package com.salientsys.salientandroidtest.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.salientsys.salientandroidtest.data.entity.Album

@Dao
interface AlbumDao {
    @Query("SELECT * FROM album")
    fun getAlbums(): LiveData<List<Album>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<Album>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(album: Album)

    @Query("SELECT * FROM album WHERE id = :id")
    fun getAlbumById(id: Int): LiveData<Album>
}