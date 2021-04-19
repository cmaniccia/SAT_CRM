package com.salientsys.salientandroidtest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(
    @PrimaryKey
    val id: Int,
    val body: String,
    val email: String,
    val name: String,
    val postId: Int
)