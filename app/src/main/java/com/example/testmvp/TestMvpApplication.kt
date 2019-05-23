package com.example.testmvp

import android.app.Application
import android.arch.persistence.room.Room
import com.example.testmvp.model.database.Database

class TestMvpApplication : Application() {

    companion object {
        lateinit var instance: TestMvpApplication
    }

    private lateinit var database: Database

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDb()
    }

    private fun initDb() {
        database = Room.databaseBuilder(this, Database::class.java, "database")
            .build()
    }

    fun getDb(): Database {
        return database
    }
}