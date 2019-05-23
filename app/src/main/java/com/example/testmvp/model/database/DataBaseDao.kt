package com.example.testmvp.model.database

import android.arch.persistence.room.*
import com.example.testmvp.entity.Post
import io.reactivex.Flowable


@Dao
interface DataBaseDao {

    @Query("SELECT * FROM post")
    fun getAll(): Flowable<List<Post>>

    @Query("SELECT * FROM post WHERE id = :id")
    fun getById(id: Long): Flowable<Post>

    @Insert
    fun insert(post: Post)

    @Insert
    fun insert(posts: List<Post>)

    @Update
    fun update(post: Post)

    @Delete
    fun delete(post: Post)
}