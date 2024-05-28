package com.bkcoding.garagegurufyp_admin.repository

import com.bkcoding.garagegurufyp_admin.dto.Garage
import com.bkcoding.garagegurufyp_admin.ui.home.model.Customer
import kotlinx.coroutines.flow.Flow
import com.bkcoding.garagegurufyp_admin.util.Result

interface AdminRepository {
    fun getGarages(): Flow<Result<List<Garage>>>

    fun getCustomer(): Flow<Result<List<Customer>>>

    fun updateGarageStatus(garageId: String, status: String): Flow<Result<String>>
}