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

//curl -v -u dd97fb58-7d2e-4d64-acd5-eead59d30b02: "https://stream.companieshouse.gov.uk/companies"
//curl -XGET -u df4bcd93-336a-4352-93fe-9ac7187edb4e: "https://api.company-information.service.gov.uk/company/00000006"
//curl -XGET -u df4bcd93-336a-4352-93fe-9ac7187edb4e: "https://api.company-information.service.gov.uk/search/companies?q=pig"