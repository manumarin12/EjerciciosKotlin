package com.movil.ejercicio03

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("number") val number : String,
    @SerializedName("title") val title : String,
    @SerializedName("releaseDate") val releaseDate : String,
    @SerializedName("pages") val pages : String,
)

lateinit var listaLibros: List<Data>