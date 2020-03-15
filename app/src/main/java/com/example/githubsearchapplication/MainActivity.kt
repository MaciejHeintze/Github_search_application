package com.example.githubsearchapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.githubsearchapplication.data.value_object.repositories.Item
import com.example.githubsearchapplication.data.value_object.repositories.RepositoryDetails
import com.example.githubsearchapplication.repository_details.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_main.*

const val GITHUB_URL = "https://api.github.com/search/repositories?q="
class MainActivity : AppCompatActivity() {

    private lateinit var repositoryAdapter: RepositoryAdapter
    private val dataList: MutableList<Item> = mutableListOf()
    private var inputRepositoryName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        search_user_button.setOnClickListener {
            clearData()
            hideKeyboard()
            setupRecyclerView()
            initData()
        }
    }

    private fun setupRecyclerView(){
        repositoryAdapter= RepositoryAdapter(dataList)
        recycler_view_id.layoutManager= LinearLayoutManager(this)
        recycler_view_id.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        recycler_view_id.adapter=repositoryAdapter
    }

    private fun initData(){
        inputRepositoryName = search_user_edit_text.text.toString()

        AndroidNetworking.initialize(this)
        AndroidNetworking.get("${GITHUB_URL}${inputRepositoryName}")
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

    private fun clearData(){
        if (dataList.size > 0){
            dataList.clear()
        }
    }

    private fun AppCompatActivity.hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}



