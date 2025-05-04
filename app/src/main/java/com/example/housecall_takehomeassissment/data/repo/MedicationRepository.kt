package com.example.housecall_takehomeassissment.data.repo

import com.example.housecall_takehomeassissment.data.localstorage.dao.DrugDao
import com.example.housecall_takehomeassissment.data.localstorage.entity.DrugEntity
import com.example.housecall_takehomeassissment.data.models.ConceptProperty
import com.example.housecall_takehomeassissment.data.service.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MedicationRepository @Inject constructor(
    private val apiService: ApiService,
    private val drugDao: DrugDao
) {
    suspend fun getDrugsByName(name: String): List<ConceptProperty> {
        return try {
            val response = apiService.getDrugs(name)
            val conceptGroups = response.drugGroup.conceptGroup ?: return emptyList()

            val sbdConcepts = conceptGroups
                .filter { it.tty == "SBD" }
                .flatMap { it.conceptProperties ?: emptyList() }
                .take(10)

            sbdConcepts
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getAllDrugs(userId: String): Flow<List<DrugEntity>> {
        return drugDao.getAllDrugs(userId)
    }

    suspend fun insertDrugs(drugs: DrugEntity): Long = drugDao.insertDrugs(drugs)

    suspend fun deleteDrug(drug: DrugEntity) {
        drugDao.deleteDrug(drug)
    }
}