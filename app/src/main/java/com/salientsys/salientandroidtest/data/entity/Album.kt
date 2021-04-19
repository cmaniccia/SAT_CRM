package com.salientsys.salientandroidtest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    @PrimaryKey
    val id: Int,
    val title: String,
    val userId: Int
)