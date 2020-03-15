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
import com.example.githubsearchapplication.data.value_object.repository_details.Repository
import kotlinx.android.synthetic.main.activity_repository_details.*


class SingleRepositoryDetails : AppCompatActivity() {

    private lateinit var viewModel: RepositoryDetailsViewModel
    private lateinit var repositoryDetailsRepository: RepositoryDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)

        val apiService : RepoDBInterface = RepoClient.getClient()
        repositoryDetailsRepository = RepositoryDetailsRepository(apiService)

        val actionbar = supportActionBar
        actionbar!!.title = "Repository details"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(SEARCHED_KEY_WORD, 0)

        viewModel = getViewModel(id)

        viewModel.repoDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            progress_bar_id.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            error_text_view_id.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE

        })

    }

    private fun getViewModel(id: Int): RepositoryDetailsViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return RepositoryDetailsViewModel(repositoryDetailsRepository,id) as T
            }
        })[RepositoryDetailsViewModel::class.java]
    }

    fun bindUI(it: Repository){

        repo_name_id?.text = "Repository name: " + it.name
        user_name_id?.text = "User name: " + it.owner.login

        val repoIcon = it.owner.avatarUrl
        Glide.with(this)
            .load(repoIcon)
            .into(iv_movie_poster)

        repo_description_id?.text = "Description: "+ it.description
        access_text_view_id?.text = "Access: " + it.private
        repo_type_id?.text = "Type: " + it.owner.type
        repo_html_address_id?.text = "Web address: " + it.owner.htmlUrl
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
