package com.example.githubsearchapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.githubsearchapplication.data.value_object.Item
import com.example.githubsearchapplication.data.value_object.RepositoryDetails
import com.example.githubsearchapplication.repository_details.RepositoryAdapter
import com.example.githubsearchapplication.repository_details.SingleRepositoryDetails
import kotlinx.android.synthetic.main.activity_main.*

const val GITHUB_URL = "https://api.github.com/search/repositories?q="
class MainActivity : AppCompatActivity() {

    private lateinit var repositoryAdapter: RepositoryAdapter
    private val dataList: MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoryAdapter= RepositoryAdapter(dataList)
        recycler_view_id.layoutManager= LinearLayoutManager(this)
        recycler_view_id.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        recycler_view_id.adapter=repositoryAdapter


        AndroidNetworking.initialize(this)
        AndroidNetworking.get("${GITHUB_URL}maciej")
            .build()
            .getAsObject(RepositoryDetails::class.java, object :
                ParsedRequestListener<RepositoryDetails> {
                override fun onResponse(response: RepositoryDetails) {
                    dataList.addAll(response.items)
                    repositoryAdapter.notifyDataSetChanged()
                }
                override fun onError(anError: ANError?) {
                    Toast.makeText(applicationContext, "Something went wrong:${anError}",Toast.LENGTH_LONG).show()
                }
            })
    }
}



