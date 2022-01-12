package com.android.companieshousestreaming.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.companieshousestreaming.data.Repository
import com.android.companieshousestreaming.models.StreamResponse
import com.android.companieshousestreaming.models.SearchResultList
import com.android.companieshousestreaming.models.SearchResultCompany
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompaniesViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    val streamList = repository.companiesListMutableState.asReversed()
    var streamConnectionStatus = repository.connectionStatus
    var streamSelectedCompany by mutableStateOf<StreamResponse?>(null)
    var searchList = mutableStateListOf<SearchResultList.Item?>()
    var searchQuery = mutableStateOf("")
    var searchSelectedCompany by mutableStateOf<SearchResultCompany?>(null)

    fun startStream() {
        repository.getStream()
    }

    fun stopStream() {
        repository.cancelStream()
        streamConnectionStatus.value = StreamConnectionStatus.Idle
    }

    fun getSearchResults(query: String) {
        viewModelScope.launch {
            searchList.clear()
            repository.getSearchResults(query).items?.let {
                searchList.addAll(
                    it
                )
            }
        }
        searchQuery.value = query
    }

    fun searchCompany(companyNumber: String) {
        viewModelScope.launch {
            searchSelectedCompany = repository.searchCompany(companyNumber)
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