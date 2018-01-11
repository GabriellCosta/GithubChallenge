package me.tigrao.service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


internal class ServiceWrapper constructor(private val api: ServiceEndpoint) {

    fun search(repositoryRequest: RepositoryRequest, enqueueHandler: EnqueueHandler<RepositoryDTO>) {
        api.search(repositoryRequest.language, repositoryRequest.sort, repositoryRequest.page)
                .enqueue(object : InnerCallback<RepositoryDTO>() {

            override fun onResponse(call: Call<RepositoryDTO>, response: Response<RepositoryDTO>) {
                enqueueHandler.handler(response)
            }

        })
    }

    fun pullRequests(pullRequestRequest: PullRequestRequest,
                     enqueueHandler: EnqueueHandler<List<PullRequestVO>>) {
        api.fetchPullRequests(pullRequestRequest.user, pullRequestRequest.repository)
                .enqueue(object : InnerCallback<List<PullRequestVO>>() {

                    override fun onResponse(call: Call<List<PullRequestVO>>,
                                            response: Response<List<PullRequestVO>>) {
                        enqueueHandler.handler(response)
                    }

                })
    }

    private abstract class InnerCallback<T> : Callback<T> {

        override fun onFailure(call: Call<T>?, t: Throwable?) {

        }

    }

}