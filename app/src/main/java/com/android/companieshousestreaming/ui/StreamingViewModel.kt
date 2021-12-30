package com.android.companieshousestreaming.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.companieshousestreaming.data.Repository
import com.android.companieshousestreaming.models.JsonResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompaniesViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    val companyList = repository.companiesListMutableState.asReversed()
    var connectionStatus = repository.connectionStatus
    var selectedCompany by mutableStateOf<JsonResponse?>(null)

    init {
        getStream()
    }

    fun getStream(){
        viewModelScope.launch {
            repository.getStream()
        }
    }
}

sealed class StreamConnectionStatus(var status: String) {
    object Connecting : StreamConnectionStatus("Connecting to stream")
    object Successful : StreamConnectionStatus("Successfully connected to stream")
    object ResponseNull : StreamConnectionStatus("Response was null")
    object Error : StreamConnectionStatus("Error connecting to stream")
    object Unsuccessful : StreamConnectionStatus("Unsuccessful connection to stream")
    object Failed : StreamConnectionStatus("Failed to connect")
    object Idle : StreamConnectionStatus("Idle")
}