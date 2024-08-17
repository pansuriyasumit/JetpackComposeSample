package com.fifteen11.jetpacksamplelibrary.data.remote.api

import com.fifteen11.jetpacksamplelibrary.data.model.MedicineResponse
import retrofit2.Response
import retrofit2.http.GET

const val MEDICINE_API_ENDPOINT = "/v3/b/668bbc3aad19ca34f8848978?meta=false"

interface MedicineApiService {

    /**
     * Set Dynamic Header
     */
    @GET(MEDICINE_API_ENDPOINT)
    suspend fun getAllMedicine(): Response<MedicineResponse>
}