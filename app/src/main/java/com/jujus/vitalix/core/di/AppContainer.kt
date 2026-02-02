package com.jujus.vitalix.core.di
import android.content.Context
import com.jujus.vitalix.BuildConfig
import com.jujus.vitalix.core.network.VitaLixApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer (context: Context) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val vitaLixApi : VitaLixApi by lazy {
        retrofit.create(VitaLixApi::class.java)
    }





}