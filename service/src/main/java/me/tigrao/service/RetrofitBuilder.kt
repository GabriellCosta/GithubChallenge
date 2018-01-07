package me.tigrao.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


internal object RetrofitBuilder {

    fun build(baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

}