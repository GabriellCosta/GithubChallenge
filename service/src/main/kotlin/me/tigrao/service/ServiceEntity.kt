package me.tigrao.service

import com.google.gson.annotations.SerializedName

data class OwnerVO(@SerializedName("avatar_url") val avatarUrl: String,
                   @SerializedName("login") val login: String)

data class PullRequestVO(@SerializedName("title") val title: String,
                         @SerializedName("body") val body: String,
                         @SerializedName("created_at") val createdAt: String,
                         @SerializedName("user") val user: OwnerVO)

data class RepositorieDTO(@SerializedName("total_count") val totalCount: Int,
                          @SerializedName("incomplete_results") val incompleteResult: Boolean,
                          @SerializedName("items") val items: List<RepositorieVO>)

data class RepositorieVO(@SerializedName("name") val name: String,
                         @SerializedName("description") val description: String,
                         @SerializedName("forks") val forks: Int,
                         @SerializedName("stargazers_count") val starts: Int,
                         @SerializedName("owner") val owner: OwnerVO)