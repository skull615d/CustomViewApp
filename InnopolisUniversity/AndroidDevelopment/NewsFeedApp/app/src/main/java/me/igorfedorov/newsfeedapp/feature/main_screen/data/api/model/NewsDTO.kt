package me.igorfedorov.newsfeedapp.feature.main_screen.data.api.model

import com.google.gson.annotations.SerializedName

data class NewsDTO(
    @SerializedName("articles")
    val articlesDTO: List<ArticleDTO>,
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("totalResults")
    val totalResults: Int
)