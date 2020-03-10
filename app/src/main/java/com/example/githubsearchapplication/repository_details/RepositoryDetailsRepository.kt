package com.example.githubsearchapplication.repository_details

import androidx.lifecycle.LiveData
import com.example.githubsearchapplication.data.api.RepoDBInterface
import com.example.githubsearchapplication.data.repository.RepoNetDataSource
import com.example.githubsearchapplication.data.value_object.NetworkState
import com.example.githubsearchapplication.data.value_object.RepositoryDetails
import io.reactivex.disposables.CompositeDisposable

class RepositoryDetailsRepository(private val apiService : RepoDBInterface) {

    lateinit var repoNetDataSource: RepoNetDataSource

    fun fetchRepoDetails(
        compositeDisposable: CompositeDisposable,
        name: String
    ): LiveData<RepositoryDetails> {

        repoNetDataSource =
            RepoNetDataSource(apiService, compositeDisposable)
        repoNetDataSource.fetchRepoDetails(name)

        return repoNetDataSource.downloadedResponse

    }

    fun getRepoDetailsNetState(): LiveData<NetworkState> {
        return repoNetDataSource.networkState
    }


}