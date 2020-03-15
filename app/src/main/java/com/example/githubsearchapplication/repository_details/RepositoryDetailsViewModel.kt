package com.example.githubsearchapplication.repository_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearchapplication.data.value_object.NetworkState
import com.example.githubsearchapplication.data.value_object.repositories.RepositoryDetails
import com.example.githubsearchapplication.data.value_object.repository_details.Repository
import io.reactivex.disposables.CompositeDisposable

class RepositoryDetailsViewModel(private val repoRepository : RepositoryDetailsRepository, id: Int)  : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  repoDetails : LiveData<Repository> by lazy {
        repoRepository.fetchRepoDetails(compositeDisposable, id)
    }

    val networkState : LiveData<NetworkState> by lazy {
        repoRepository.getRepoDetailsNetState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
    }