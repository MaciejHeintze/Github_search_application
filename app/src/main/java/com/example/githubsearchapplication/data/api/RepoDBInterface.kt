package com.example.githubsearchapplication.data.api

import com.example.githubsearchapplication.data.value_object.RepositoryDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoDBInterface {

    //https://api.github.com/search/repositories?q=android

    @GET("search/repositories?q={name}")
    fun getRepositoryByName(@Path("name")name: String): Single<RepositoryDetails>
}