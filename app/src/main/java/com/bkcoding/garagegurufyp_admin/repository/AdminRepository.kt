package com.bkcoding.garagegurufyp_admin.repository

import com.bkcoding.garagegurufyp_admin.dto.Garage
import kotlinx.coroutines.flow.Flow
import com.bkcoding.garagegurufyp_admin.util.Result

interface AdminRepository {
    fun getGarages(): Flow<Result<List<Garage>>>

    fun updateGarageStatus(garageId: String, status: String): Flow<Result<String>>
}