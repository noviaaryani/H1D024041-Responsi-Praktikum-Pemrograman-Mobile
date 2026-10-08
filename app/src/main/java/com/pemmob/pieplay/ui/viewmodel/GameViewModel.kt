package com.pemmob.pieplay.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pemmob.pieplay.data.model.Game
import com.pemmob.pieplay.data.model.GameDetail
import com.pemmob.pieplay.data.repository.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val repository = GameRepository()

    private val _games = MutableStateFlow<List<Game>>(emptyList())
    val games: StateFlow<List<Game>> = _games

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _gameDetail = MutableStateFlow<GameDetail?>(null)
    val gameDetail: StateFlow<GameDetail?> = _gameDetail
    
    private val _isDetailLoading = MutableStateFlow(false)
    val isDetailLoading: StateFlow<Boolean> = _isDetailLoading

    init {
        fetchGames()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        fetchGames(if (query.isBlank()) null else query)
    }

    fun fetchGames(query: String? = null) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            
            val result = repository.getGames(query)
            result.onSuccess {
                _games.value = it.results
            }.onFailure {
                _errorMessage.value = it.message ?: "Unknown Error"
            }
            
            _isLoading.value = false
        }
    }

    fun fetchGameDetail(id: Int) {
        viewModelScope.launch {
            _isDetailLoading.value = true
            _gameDetail.value = null
            
            val result = repository.getGameDetail(id)
            result.onSuccess {
                _gameDetail.value = it
            }.onFailure {
                // handle error
            }
            
            _isDetailLoading.value = false
        }
    }
}
