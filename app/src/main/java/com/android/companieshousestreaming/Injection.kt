package com.android.companieshousestreaming

import androidx.lifecycle.ViewModelProvider

object Injection {

    private fun provideRepository(): Repository {
        return Repository(RetrofitService.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideRepository())
    }

}