package com.android.companieshousestreaming.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.android.companieshousestreaming.BuildConfig
import com.android.companieshousestreaming.di.AppModule
import com.android.companieshousestreaming.ui.StreamConnectionStatus
import com.android.companieshousestreaming.models.StreamResponse
import com.android.companieshousestreaming.models.SearchResultList
import com.android.companieshousestreaming.models.SearchResultCompany
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStreamReader
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    @AppModule.RetrofitStream private val streamingService: RetrofitService,
    @AppModule.RetrofitRest private val restService: RetrofitService,
) {

    var connectionStatus = mutableStateOf<StreamConnectionStatus?>(StreamConnectionStatus.Idle)
    var companiesListMutableState = mutableStateListOf<StreamResponse>()
    private var companiesStream: Call<ResponseBody>? = null
    private val streamingKey = BuildConfig.STREAMING_KEY
    private val restKey = BuildConfig.REST_KEY

    fun getStream() {
        companiesStream = streamingService.getStream(streamingKey)
        companiesStream?.enqueue(streamResponse)

        connectionStatus.value = StreamConnectionStatus.Connecting
    }

    fun cancelStream(){
        companiesStream?.cancel()
    }

    private val streamResponse: Callback<ResponseBody> = object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.isSuccessful) {

                connectionStatus.value = StreamConnectionStatus.Successful

                Thread(
                    Runnable {
                        try {

                            val reader = JsonReader(InputStreamReader(response.body()!!.byteStream()))
                            val gson = GsonBuilder().create()

                            while (true) {
                                val j = gson.fromJson<JsonObject>(reader, JsonObject::class.java)

                                if (j.getAsJsonObject("data") != null) {
                                    val company = gson.fromJson(j, StreamResponse::class.java)
                                    companiesListMutableState.add(company)

                                } else {
                                    connectionStatus.value = StreamConnectionStatus.ResponseNull
                                }
                            }
                        } catch (e: Exception) {
                            connectionStatus.value = StreamConnectionStatus.Error
                        }
                    }
                ).start()
            } else {
                connectionStatus.value = StreamConnectionStatus.Unsuccessful
            }
        }

        override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
            connectionStatus.value = StreamConnectionStatus.Failed
        }
    }

    suspend fun getSearchResults(query: String): SearchResultList {
        return restService.searchCompanies(restKey, query, "50", "1")
    }

    suspend fun searchCompany(companyNumber: String): SearchResultCompany? {
        return restService.getCompany(restKey, companyNumber)
    }
}