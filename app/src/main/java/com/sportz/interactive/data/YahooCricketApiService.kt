package com.sportz.interactive.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sportz.interactive.data.network.ConnectivityInterceptor
import com.sportz.interactive.data.network.response.Match
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface YahooCricketApiService {

    @GET("nzin01312019187360.json")
    fun getMatchAsync(): Deferred<Match>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): YahooCricketApiService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder().url(url).build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://cricket.yahoo.net/sifeeds/cricket/live/json/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(YahooCricketApiService::class.java)
        }
    }
}