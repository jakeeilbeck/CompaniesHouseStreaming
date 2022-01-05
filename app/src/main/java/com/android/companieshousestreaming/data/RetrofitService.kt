package com.android.companieshousestreaming.data

import com.android.companieshousestreaming.models.SearchResultList
import com.android.companieshousestreaming.models.SearchResultCompany
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @Streaming
    @GET("companies")
    fun getStream(
        @Header("Authorization") token: String
    ): Call<ResponseBody>

    @GET("search/companies?")
    suspend fun searchCompanies(
        @Header("Authorization") token: String,
        @Query("q") query: String,
        @Query("items_per_page") itemsPerPage: String,
        @Query("start_index") startIndex: String
    ): SearchResultList

    @GET("company/{companyNumber}")
    suspend fun getCompany(
        @Header("Authorization") token: String,
        @Path("companyNumber") companyNumber: String
    ): SearchResultCompany

    companion object {
        const val STREAM_BASE_URL = "https://stream.companieshouse.gov.uk/"
        const val REST_BASE_URL = "https://api.company-information.service.gov.uk/"
    }
}