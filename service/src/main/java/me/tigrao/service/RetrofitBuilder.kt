package me.tigrao.service

import retrofit2.Retrofit


internal object RetrofitBuilder {

    fun build(baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .build()
    }

}