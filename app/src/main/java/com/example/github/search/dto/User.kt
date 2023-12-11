package com.example.github.search.dto

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String,
    @SerializedName("repos_url") val reposUrl: String
)
