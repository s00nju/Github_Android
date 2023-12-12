package com.example.github.repoDetail.repository

import com.example.github.common.api.retrofit
import com.example.github.repoList.api.Repo
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

val repoDetailApi: RepoDetailRepository by lazy { retrofit.create() }

interface RepoDetailRepository {

    @GET
    fun getRepoDetailResult(
        @Url url: String
    ): Call<Repo>

}