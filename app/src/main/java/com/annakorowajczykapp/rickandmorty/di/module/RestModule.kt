package com.annakorowajczykapp.rickandmorty.di.module

import com.annakorowajczykapp.rickandmorty.utils.ApiService
import com.annakorowajczykapp.rickandmorty.utils.AppUrl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RestModule {

    @Provides
    fun providesGson() = Gson()

    @Provides
    fun providesOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(3, TimeUnit.SECONDS)
        .readTimeout(3, TimeUnit.SECONDS)
        .build()

    @Provides
    fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(AppUrl.SERVER_API)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    fun providesApi(gson: Gson, okHttpClient: OkHttpClient): ApiService {
        return providesRetrofit(gson, okHttpClient).create(ApiService::class.java)
    }

}