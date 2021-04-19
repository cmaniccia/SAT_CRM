package com.salientsys.salientandroidtest.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)