package com.android.companieshousestreaming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.android.companieshousestreaming.models.JsonResponse
import kotlinx.coroutines.flow.Flow

class ViewModel(private val repository: Repository) : ViewModel() {

    fun getStream(): Flow<PagingData<JsonResponse.Data>> {

        val result: Flow<PagingData<JsonResponse.Data>> = repository.getStream().cachedIn(viewModelScope)

        return result
    }
}