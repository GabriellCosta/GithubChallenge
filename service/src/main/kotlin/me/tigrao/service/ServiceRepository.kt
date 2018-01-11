package me.tigrao.service

interface ServiceRepository {

    fun fetchRepositories(repositoryRequest: RepositoryRequest, enqueueHandler: EnqueueHandler<RepositoryDTO>)

    fun fetchPullRequest(pullRequestRequest: PullRequestRequest, enqueueHandler: EnqueueHandler<List<PullRequestVO>>)

}


class ServiceRepositoryImpl internal constructor(private val serviceWrapper: ServiceWrapper):
        ServiceRepository {

    companion object {

        private const val GITHUB_ENDPOINT = "http://api.github.com"

        fun newInstance(): ServiceRepository {
            val create = RetrofitBuilder.build(GITHUB_ENDPOINT)
                    .create(ServiceEndpoint::class.java)
            return ServiceRepositoryImpl(ServiceWrapper(create))
        }
    }

    override fun fetchRepositories(repositoryRequest: RepositoryRequest,
                                   enqueueHandler: EnqueueHandler<RepositoryDTO>) {
        serviceWrapper.search(repositoryRequest, enqueueHandler)
    }

    override fun fetchPullRequest(pullRequestRequest: PullRequestRequest,
                                  enqueueHandler: EnqueueHandler<List<PullRequestVO>>) {
        serviceWrapper.pullRequests(pullRequestRequest, enqueueHandler)
    }

}