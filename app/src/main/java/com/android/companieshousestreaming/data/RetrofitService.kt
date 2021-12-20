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

//curl -v -u Hk-gPF7eRYkP8N5NQOLrTJMRnFk8anorwI7iLXrP: "https://stream.companieshouse.gov.uk/companies"
//curl -v -u 39jvHc8UnL3z2KQr0oXM43-DBkrumHLWhqqCt7DZ: "https://stream.companieshouse.gov.uk/companies"
//curl -v -u 39jvHc8UnL3z2KQr0oXM43-DBkrumHLWhqqCt7DZ: "https://stream.company-information.service.gov.uk/companies"