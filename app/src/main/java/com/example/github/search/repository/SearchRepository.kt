package com.example.github.search.repository

import com.example.github.common.api.retrofit
import com.example.github.search.api.SearchResponse
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val searchApi: SearchRepository by lazy { retrofit.create()}
interface SearchRepository {

//    @GET("search/users")
//    fun getSearchResult(
//        @Query("q") query: String
//    ): Call<SearchResponse>

    @GET("users/{user}")
    fun getSearchResult(
        @Path("user") user: String
    ): Call<SearchResponse>
}