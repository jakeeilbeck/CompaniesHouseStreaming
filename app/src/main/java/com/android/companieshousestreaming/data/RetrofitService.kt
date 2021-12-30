package com.android.companieshousestreaming.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Streaming

interface RetrofitService {
    @Streaming
    @GET("companies")
    fun getStream(@Header("Authorization") token: String): Call<ResponseBody>

    companion object{
        const val BASE_URL = "https://stream.companieshouse.gov.uk/"
    }
}