package com.android.companieshousestreaming.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.companieshousestreaming.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompaniesViewModel @Inject constructor(
    private val repository: Repository,
    ): ViewModel() {

    val companyList = repository.companiesListMutableState.asReversed()
    var connectionStatus = repository.connectionStatus

    init {
        viewModelScope.launch {
            repository.getStream()
        }
    }
}

sealed class StreamConnectionStatus(var status: String){
    object Connecting : StreamConnectionStatus("Connecting to stream")
    object Successful : StreamConnectionStatus("Successfully connected to stream")
    object ResponseNull : StreamConnectionStatus("Response was null")
    object Error : StreamConnectionStatus("Error connecting to stream")
    object Unsuccessful : StreamConnectionStatus("Unsuccessful connection to stream")
    object Failed : StreamConnectionStatus("Failed to connect")
    object Idle : StreamConnectionStatus("Idle")
}