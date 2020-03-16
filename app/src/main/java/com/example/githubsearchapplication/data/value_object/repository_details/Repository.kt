package com.example.githubsearchapplication.data.value_object.repository_details


import com.google.gson.annotations.SerializedName

data class Repository(

    val owner: Owner,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    val language: Any,
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    val private: String,
    val subscribersCount: Int
)