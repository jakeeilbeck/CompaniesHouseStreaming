package com.android.companieshousestreaming

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.android.companieshousestreaming.models.JsonResponse
import kotlinx.coroutines.flow.Flow

class Repository(private val service: RetrofitService) {
    fun getStream(): Flow<PagingData<JsonResponse.Data>> {
        return Pager(
            config = PagingConfig(pageSize = 50, maxSize = 200),
            pagingSourceFactory = {
                PagingSource(
                    service
                )
            }
        ).flow
    }
}