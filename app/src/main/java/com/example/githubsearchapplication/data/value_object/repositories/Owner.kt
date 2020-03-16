package com.example.githubsearchapplication.data.value_object.repositories

import com.google.gson.annotations.SerializedName

data class Owner(

    @SerializedName("avatar_url")
    val avatarUrl: String,
    val login: String,
    val type: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val id: Int,
    val url: String
)