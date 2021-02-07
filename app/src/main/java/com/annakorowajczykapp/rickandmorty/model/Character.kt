package com.annakorowajczykapp.rickandmorty.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Character(
    @SerializedName("info")
    var info: Info,
    @SerializedName("results")
    val resultList: List<ResultsListData>
) : Serializable