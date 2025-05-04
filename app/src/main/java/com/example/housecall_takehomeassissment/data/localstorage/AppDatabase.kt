package com.example.housecall_takehomeassissment.data.localstorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.housecall_takehomeassissment.data.localstorage.dao.DrugDao
import com.example.housecall_takehomeassissment.data.localstorage.entity.DrugEntity

@Database(entities = [DrugEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun drugDao(): DrugDao
}