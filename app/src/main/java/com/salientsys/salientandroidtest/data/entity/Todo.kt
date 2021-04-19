package com.salientsys.salientandroidtest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey
    val id: Int,
    val completed: Boolean,
    val title: String,
    val userId: Int
)