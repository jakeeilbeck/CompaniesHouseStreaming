package com.android.companieshousestreaming.di

import com.android.companieshousestreaming.data.RetrofitService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Retrofit dependencies
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitStream

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RetrofitRest

    @Provides
    @Singleton
    @RetrofitStream
    fun provideRetrofitStream(okHttpClient: OkHttpClient, gson: Gson): RetrofitService =
        Retrofit.Builder()
            .baseUrl(RetrofitService.STREAM_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(RetrofitService::class.java)

    @Provides
    @Singleton
    @RetrofitRest
    fun provideRetrofitRest(okHttpClient: OkHttpClient, gson: Gson): RetrofitService =
        Retrofit.Builder()
            .baseUrl(RetrofitService.REST_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(RetrofitService::class.java)

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder()
            .setLenient()
            .setPrettyPrinting()
            .create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
}