package com.example.centrosimulacionenfermeria.data.repository

import com.example.centrosimulacionenfermeria.data.model.Post
import com.example.centrosimulacionenfermeria.data.remote.RetrofitInstance

class PostRepository {

    suspend fun getPosts(): List<Post> {

        return RetrofitInstance.api.getPosts()
    }
}