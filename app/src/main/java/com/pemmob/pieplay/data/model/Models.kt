package com.pemmob.pieplay.data.model

import com.google.gson.annotations.SerializedName

data class GameResponse(
    val results: List<Game>
)

data class Game(
    val id: Int,
    val name: String,
    val rating: Double,
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?
)

data class GameDetail(
    val id: Int,
    val name: String,
    val rating: Double,
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    val description: String?,
    @SerializedName("description_raw")
    val descriptionRaw: String?
)
