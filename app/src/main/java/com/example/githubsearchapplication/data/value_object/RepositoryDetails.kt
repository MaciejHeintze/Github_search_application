package com.example.githubsearchapplication.data.value_object


import com.google.gson.annotations.SerializedName

data class RepositoryDetails(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<Item>,
    @SerializedName("total_count")
    val totalCount: Int
)