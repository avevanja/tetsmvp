package com.example.testmvp.model.network

import com.example.testmvp.AppConstants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitBuilder {

    companion object {
        private lateinit var retrofit: Retrofit
        val TIMEOUT = 30L

        private var api = getRetrofitBuilderWithoutRx()?.create(Api::class.java)
        private fun getRetrofitBuilderWithoutRx(): Retrofit {
            if (!this::retrofit.isInitialized) {
                val gson = GsonBuilder().setLenient().create()
                retrofit = Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getHttpClient())
                    .build()
            }
            return retrofit
        }

        fun getApi(): Api {
            return api
        }

        private fun getHttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS).build()
        }

    }

}