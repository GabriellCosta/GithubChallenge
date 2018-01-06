package me.tigrao.service

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ServiceEndpoint {

    @GET("search/repositories")
    fun search(@Query("q") language: String,
               @Query("sort") sort: String,
               @Query("page") page: Int): Call<RepositorieDTO>

    @GET("repos/{user}/{repo}/pulls")
    fun fetchPullRequests(@Path("user") user: String,
                          @Path("repo") repository: String): Call<List<PullRequestVO>>

}