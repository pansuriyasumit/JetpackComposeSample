package com.fifteen11.jetpacksamplelibrary.data.local.repository

import com.fifteen11.jetpacksamplelibrary.data.local.UserLoginHistoryEntity
import kotlinx.coroutines.flow.Flow

interface MedicineLocalRepository {

    suspend fun insertUserLoginHistory(userEntity: UserLoginHistoryEntity)
    suspend fun getUserLoginHistory(): Flow<List<UserLoginHistoryEntity>>
}