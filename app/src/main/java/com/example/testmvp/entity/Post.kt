package com.example.testmvp.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Post(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("body")
    val body: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)