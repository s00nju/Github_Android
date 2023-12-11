package com.example.github.repoList.repository

import com.example.github.common.api.retrofit
import com.example.github.repoList.api.Repo
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

val repoApi: RepoListRepository by lazy { retrofit.create() }

interface RepoListRepository {

//    @GET
//    fun getRepoListResult(
//        @Url url: String
//    ): Call<List<Repo>>

    @GET
    fun getRepoListResult(
        @Url url: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = 10
    ): Call<List<Repo>>

}