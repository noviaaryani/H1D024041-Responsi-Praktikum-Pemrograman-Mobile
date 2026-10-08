package com.pemmob.pieplay.data.repository

import com.pemmob.pieplay.data.model.GameDetail
import com.pemmob.pieplay.data.model.GameResponse
import com.pemmob.pieplay.data.remote.RetrofitClient

class GameRepository {
    private val api = RetrofitClient.instance
    private val apiKey = "f08ce2a5683a48e697acc43205c0b312"

    suspend fun getGames(searchQuery: String? = null): Result<GameResponse> {
        return try {
            val response = api.getGames(apiKey = apiKey, searchQuery = searchQuery)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getGameDetail(id: Int): Result<GameDetail> {
        return try {
            val response = api.getGameDetail(id = id, apiKey = apiKey)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
