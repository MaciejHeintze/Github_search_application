package com.example.githubsearchapplication.data.api

import com.example.githubsearchapplication.data.value_object.repository_details.Repository
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoDBInterface {

    @GET("repositories/{id}")
    fun getRepositoryByName(@Path("id")id: Int): Single<Repository>
}