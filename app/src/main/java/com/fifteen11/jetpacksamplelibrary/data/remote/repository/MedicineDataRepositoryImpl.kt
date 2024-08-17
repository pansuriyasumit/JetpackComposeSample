package com.fifteen11.jetpacksamplelibrary.data.remote.repository

import com.fifteen11.jetpacksamplelibrary.data.remote.api.MedicineApiService
import com.fifteen11.jetpacksamplelibrary.data.model.MedicineResponse
import com.fifteen11.jetpacksamplelibrary.data.remote.repository.MedicineDataRepository
import javax.inject.Inject

class MedicineDataRepositoryImpl @Inject constructor(
    private val medicineAPI: MedicineApiService,
) : MedicineDataRepository {

    override suspend fun getMedicinesFormAPI(): MedicineResponse {
        val medicines = medicineAPI.getAllMedicine()
        return medicines.body() ?: MedicineResponse()
    }
}