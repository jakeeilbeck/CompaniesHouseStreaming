package com.android.companieshousestreaming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ViewModelFactory(private val repository: Repository): ViewModelProvider.Factory{

    @ExperimentalCoroutinesApi
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}