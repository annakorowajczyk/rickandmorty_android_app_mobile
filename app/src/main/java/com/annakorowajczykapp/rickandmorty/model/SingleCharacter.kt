package com.annakorowajczykapp.rickandmorty.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SingleCharacter (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("species")
    var species: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("origin")
    var origin: Origin,
    @SerializedName("location")
    var location: Location,
    @SerializedName("image")
    var image: String,
    @SerializedName("episode")
    val episodeList: List<String>,
    @SerializedName("url")
    var url: String,
    @SerializedName("created")
    var created: String
): Serializable