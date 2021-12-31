package com.android.companieshousestreaming.data

import com.android.companieshousestreaming.models.SearchResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Streaming

interface RetrofitService {
    @Streaming
    @GET("companies")
    fun getStream(
        @Header("Authorization") token: String
    ): Call<ResponseBody>

    @GET("companies?")
    suspend fun searchCompanies(
        @Header("Authorization") token: String,
        @Query("q") query: String,
        @Query("items_per_page") itemsPerPage: String,
        @Query("start_index") startIndex: String
    ): SearchResult

    companion object{
        const val STREAM_BASE_URL = "https://stream.companieshouse.gov.uk/"
        const val REST_BASE_URL = "https://api.company-information.service.gov.uk/search/"
    }
}