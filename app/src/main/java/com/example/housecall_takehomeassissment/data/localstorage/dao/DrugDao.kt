package com.example.housecall_takehomeassissment.data.localstorage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.housecall_takehomeassissment.data.localstorage.entity.DrugEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DrugDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrugs(drugs: DrugEntity): Long

    @Query("SELECT * FROM drugs WHERE userId = :userId")
    fun getAllDrugs(userId: String): Flow<List<DrugEntity>>

    @Delete
    suspend fun deleteDrug(drug: DrugEntity)

    @Query("SELECT COUNT(*) FROM drugs WHERE userId = :userId")
    suspend fun getUserRecordCount(userId: String): Int
}