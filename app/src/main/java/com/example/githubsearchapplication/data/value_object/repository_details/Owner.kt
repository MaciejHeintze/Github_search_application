package com.example.githubsearchapplication.data.value_object.repository_details

import com.google.gson.annotations.SerializedName

data class Owner(

    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    val login: String,
    val type: String,
    val url: String
)