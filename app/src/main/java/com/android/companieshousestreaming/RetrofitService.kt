package com.android.companieshousestreaming

import com.android.companieshousestreaming.models.JsonResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Streaming

interface RetrofitService {

    @Headers(
        "Authorization: Basic 39jvHc8UnL3z2KQr0oXM43-DBkrumHLWhqqCt7DZ"
    )
    @GET
    @Streaming
    suspend fun getStream(): JsonResponse

    companion object{
        private const val BASE_URL = "https://stream.companieshouse.gov.uk/companies"

        private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        fun create(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(RetrofitService::class.java)
        }
    }
}