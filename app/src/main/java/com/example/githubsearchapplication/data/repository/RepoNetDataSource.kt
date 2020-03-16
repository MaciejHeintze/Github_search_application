package com.example.githubsearchapplication.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubsearchapplication.data.api.RepoDBInterface
import com.example.githubsearchapplication.data.value_object.NetworkState
import com.example.githubsearchapplication.data.value_object.repository_details.Repository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepoNetDataSource(
    private val apiService : RepoDBInterface,
    private val compositeDisposable: CompositeDisposable
    ){

    private val _networkState  = MutableLiveData<NetworkState>()

    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val _downloadedResponse =  MutableLiveData<Repository>()

    val downloadedResponse: LiveData<Repository>
        get() = _downloadedResponse


    fun fetchRepoDetails(id: Int) {
        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                apiService.getRepositoryByName(id)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("RepoDetailsDataSource", it.message)
                        })
            )
        }
        catch (e: Exception){
            Log.e("RepoDetailsDataSource",e.message)
        }
    }
}
