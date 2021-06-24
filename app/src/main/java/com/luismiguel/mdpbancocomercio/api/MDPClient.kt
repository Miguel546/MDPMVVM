package com.luismiguel.mdpbancocomercio.api

import com.luismiguel.mdpbancocomercio.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MDPClient {

    private var retrofit: Retrofit
    init{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okBuilder = OkHttpClient.Builder()
        okBuilder.readTimeout(10000, TimeUnit.MILLISECONDS)
        okBuilder.connectTimeout(10000, TimeUnit.MILLISECONDS)
        okBuilder.addInterceptor(loggingInterceptor)
        val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BuildConfig.UrlBase)
            .addConverterFactory(GsonConverterFactory.create())

        retrofit = retrofitBuilder.client(okBuilder.build()).build()
    }

    companion object {
        private var INSTANCE: MDPClient ? = null

        fun getInstance(): MDPRetrofit {
            if (INSTANCE == null) {
                INSTANCE = MDPClient()
            }
            return INSTANCE?.retrofit?.create(MDPRetrofit::class.java) as MDPRetrofit
        }
    }
}