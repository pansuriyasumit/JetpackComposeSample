package com.fifteen11.jetpacksamplelibrary.data.remote.repository

import com.fifteen11.jetpacksamplelibrary.data.model.MedicineResponse

interface MedicineDataRepository {
    suspend fun getMedicinesFormAPI(): MedicineResponse
}