package me.igorfedorov.newsfeedapp.feature.main_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class ArticleDTO(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("content")
    val content: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("publishedAt")
    val publishedAt: String? = "",
    @SerializedName("source")
    val sourceDTO: SourceDTO,
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("urlToImage")
    val urlToImage: String? = ""
)