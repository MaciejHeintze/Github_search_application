package com.example.githubsearchapplication.repository_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.githubsearchapplication.R
import com.example.githubsearchapplication.data.api.RepoClient
import com.example.githubsearchapplication.data.api.RepoDBInterface
import com.example.githubsearchapplication.data.value_object.NetworkState
import com.example.githubsearchapplication.data.value_object.RepositoryDetails
import kotlinx.android.synthetic.main.activity_repository_details.*


class SingleRepositoryDetails : AppCompatActivity() {

    private lateinit var viewModel: RepositoryDetailsViewModel
    private lateinit var repositoryDetailsRepository: RepositoryDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)

        val apiService : RepoDBInterface = RepoClient.getClient()
        repositoryDetailsRepository = RepositoryDetailsRepository(apiService)

        val name = "Github_search_application"

        viewModel = getViewModel(name)

        viewModel.repoDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar_id.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            error_text_view_id.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE

        })

    }

    private fun getViewModel(name: String): RepositoryDetailsViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return RepositoryDetailsViewModel(repositoryDetailsRepository,name) as T
            }
        })[RepositoryDetailsViewModel::class.java]
    }

    fun bindUI(it: RepositoryDetails){

        repo_name_id?.text = "Repository name: " + it.items.first().name
        user_name_id?.text = "User name: " + it.items.get(index = 1).owner.login

        val repoIcon = it.items.get(index = 2).owner.avatarUrl
        Glide.with(this)
            .load(repoIcon)
            .into(iv_movie_poster)

        repo_description_id?.text = "Description: "+it.items.get(index = 3).description
        access_text_view_id?.text = "Access: " + it.items.get(index = 4).private.toString()
        repo_type_id?.text = "Type: " + it.items.get(index = 2).owner.type
        repo_html_address_id?.text = "Web address: " + it.items.get(index = 3).owner.htmlUrl
    }
}
