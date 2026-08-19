package com.example.centrosimulacionenfermeria.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.centrosimulacionenfermeria.data.model.Post
import com.example.centrosimulacionenfermeria.data.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState {

    object Loading : UiState()

    data class Success(
        val posts: List<Post>
    ) : UiState()

    data class Error(
        val message: String
    ) : UiState()
}

class PostViewModel : ViewModel() {

    private val repository =
        PostRepository()

    private val _uiState =
        MutableStateFlow<UiState>(
            UiState.Loading
        )

    val uiState: StateFlow<UiState> =
        _uiState

    init {

        fetchPosts()
    }

    fun fetchPosts() {

        viewModelScope.launch {

            _uiState.value =
                UiState.Loading

            try {

                val posts =
                    repository.getPosts()

                _uiState.value =
                    UiState.Success(posts)

            } catch (e: Exception) {

                _uiState.value =
                    UiState.Error(
                        e.message
                            ?: "Error desconocido"
                    )
            }
        }
    }
}