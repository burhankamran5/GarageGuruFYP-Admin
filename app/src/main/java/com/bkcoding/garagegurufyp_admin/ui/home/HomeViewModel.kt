package com.bkcoding.garagegurufyp_admin.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bkcoding.garagegurufyp_admin.dto.Garage
import com.bkcoding.garagegurufyp_admin.repository.AdminRepository
import com.bkcoding.garagegurufyp_admin.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val adminRepository: AdminRepository): ViewModel() {
    var homeScreenUIState by mutableStateOf<Result<List<Garage>>?>(null)

    init {
        getGarages()
    }

    private fun getGarages() = viewModelScope.launch {
        adminRepository.getGarages().collect{
            homeScreenUIState = it
        }
    }

    fun updateGarageStatus(garageId: String, status: String) = viewModelScope.launch {
        adminRepository.updateGarageStatus(garageId, status).collect{
            getGarages()
        }
    }
}