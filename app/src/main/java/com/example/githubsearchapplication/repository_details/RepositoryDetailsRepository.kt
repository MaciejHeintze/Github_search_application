package com.example.githubsearchapplication.repository_details

import androidx.lifecycle.LiveData
import com.example.githubsearchapplication.data.api.RepoDBInterface
import com.example.githubsearchapplication.data.repository.RepoNetDataSource
import com.example.githubsearchapplication.data.value_object.NetworkState
import com.example.githubsearchapplication.data.value_object.repository_details.Repository
import io.reactivex.disposables.CompositeDisposable

class RepositoryDetailsRepository(private val apiService : RepoDBInterface) {

    lateinit var repoNetDataSource: RepoNetDataSource

    fun fetchRepoDetails(
        compositeDisposable: CompositeDisposable,
        id: Int
    ): LiveData<Repository> {

        repoNetDataSource =
            RepoNetDataSource(apiService, compositeDisposable)
        repoNetDataSource.fetchRepoDetails(id)

        return repoNetDataSource.downloadedResponse
    }

    fun getRepoDetailsNetState(): LiveData<NetworkState> {
        return repoNetDataSource.networkState
    }
}