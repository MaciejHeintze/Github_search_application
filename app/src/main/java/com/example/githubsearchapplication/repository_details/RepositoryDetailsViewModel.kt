package com.example.githubsearchapplication.repository_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubsearchapplication.data.value_object.Item
import com.example.githubsearchapplication.data.value_object.NetworkState
import com.example.githubsearchapplication.data.value_object.RepositoryDetails
import io.reactivex.disposables.CompositeDisposable

class RepositoryDetailsViewModel(private val repoRepository : RepositoryDetailsRepository, name: String)  : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  repoDetails : LiveData<RepositoryDetails> by lazy {
        repoRepository.fetchRepoDetails(compositeDisposable, name)
    }

    val networkState : LiveData<NetworkState> by lazy {
        repoRepository.getRepoDetailsNetState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
    }