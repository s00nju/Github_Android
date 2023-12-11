package com.example.github.search.api

import com.example.github.search.dto.User
import com.google.gson.annotations.SerializedName

data class SearchResponse(
//    @SerializedName("total_count") val totalCount: Int,
//    @SerializedName("incomplete_results") val incompleteResults: Boolean,
//    @SerializedName("items") val items: List<User>
    @SerializedName("login") val login: String,
    @SerializedName("id") val id: Int,
    @SerializedName("url") val url: String,
    @SerializedName("repos_url") val reposUrl: String,
    @SerializedName("message") val message: String?
)