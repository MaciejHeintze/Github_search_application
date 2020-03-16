package com.example.githubsearchapplication.repository_details

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubsearchapplication.R
import com.example.githubsearchapplication.data.value_object.repositories.Item
import kotlinx.android.synthetic.main.repository_item.view.*

const val SEARCHED_KEY_WORD = "key_word"

class RepositoryAdapter (
    private val dataList: MutableList<Item>
) : RecyclerView.Adapter<GithubHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubHolder {
        context=parent.context
        return GithubHolder(
            LayoutInflater
            .from(context)
            .inflate(R.layout.repository_item,parent,false))
    }
    override fun getItemCount()= dataList.size

    override fun onBindViewHolder(holder: GithubHolder, position: Int) {

        val data = dataList[position]

        val repoName = holder.itemView.card_view_repo_name_id
        val userName = holder.itemView.card_view_user_name_id
        val repoIcon = holder.itemView.card_view_repo_icon_id
        val repoIconView = data.owner.avatarUrl

        repoName.text = data.name
        userName.text = data.owner.login

        Glide.with(context)
            .load(repoIconView)
            .into(repoIcon)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, SingleRepositoryDetails::class.java)
            intent.putExtra(SEARCHED_KEY_WORD, data.id )
            startActivity(context,intent,null)
        }
    }
}

class GithubHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private fun AdapterView.OnItemClickListener.onItemClick(items: Item) {
        onItemClick(items)
}