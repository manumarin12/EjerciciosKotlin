package com.movil.ejercicio02


import com.google.gson.annotations.SerializedName

data class Villano(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("status") val status: String,
)

data class Datos(val data:List<Villano>)
lateinit var datos: Datos