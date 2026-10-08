package com.pemmob.pieplay.data.remote

import com.pemmob.pieplay.data.model.GameDetail
import com.pemmob.pieplay.data.model.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgApi {
    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String,
        @Query("search") searchQuery: String? = null
    ): GameResponse

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") id: Int,
        @Query("key") apiKey: String
    ): GameDetail
}
