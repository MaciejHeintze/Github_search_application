package com.example.githubsearchapplication.data.value_object.repositories

import com.google.gson.annotations.SerializedName

data class Item(

    val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val owner: Owner,
    val description: String,
    val private: Boolean,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val language: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String,
    @SerializedName("watchers_count")
    val watchersCount: Int
)