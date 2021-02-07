package com.annakorowajczykapp.rickandmorty.utils

import com.annakorowajczykapp.rickandmorty.model.Character
import com.annakorowajczykapp.rickandmorty.model.SingleCharacter
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character/")
    fun getCharactersList(@Query("page") page: Int): Observable<Character>

    @GET("character/{id}")
    fun getSingleCharacter(@Path("id") id: Int): Observable<SingleCharacter>
}