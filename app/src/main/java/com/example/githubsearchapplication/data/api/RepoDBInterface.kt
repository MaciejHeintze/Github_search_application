package com.example.githubsearchapplication.data.api

import com.example.githubsearchapplication.data.value_object.repositories.RepositoryDetails
import com.example.githubsearchapplication.data.value_object.repository_details.Repository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RepoDBInterface {

    //https://api.github.com/search/repositories?q=android
    //https://api.github.com/repositories/82128465

    @GET("repositories/{id}")
    fun getRepositoryByName(@Path("id")id: Int): Single<Repository>
}