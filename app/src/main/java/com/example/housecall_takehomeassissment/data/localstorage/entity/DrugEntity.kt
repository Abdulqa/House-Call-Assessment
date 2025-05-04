package com.example.housecall_takehomeassissment.data.localstorage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drugs")
data class DrugEntity(
    @PrimaryKey(autoGenerate = false)
    val rxcui: String,
    val userId: String,
    val name: String?,
    val synonym: String?,
    val tty: String?,
    val language: String?,
    val suppress: String?,
    val umlscui: String?
)