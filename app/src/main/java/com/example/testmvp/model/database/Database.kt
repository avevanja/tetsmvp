package com.example.testmvp.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.testmvp.entity.Post

@Database(entities = [Post::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun dataBaseDao(): DataBaseDao
}