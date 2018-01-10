package me.tigrao.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

interface ServiceRepository {

    fun fetchRepositories(repositoryRequest: RepositoryRequest): LiveData<RepositorieDTO>

    fun fetchPullRequest(user: String, repositoy: String)

}


class ServiceRepositoryImpl internal constructor(private val serviceWrapper: ServiceWrapper):
        ServiceRepository {

    private val valueObserver: MutableLiveData<RepositorieDTO> = MutableLiveData()
    private val errorObserver: MutableLiveData<String> = MutableLiveData()

    init {
        serviceWrapper.errorObserver = errorObserver
        serviceWrapper.valueObserver = valueObserver
    }

    companion object {

        private const val GITHUB_ENDPOINT = "http://api.github.com"

        fun newInstance(): ServiceRepository {
            val create = RetrofitBuilder.build(GITHUB_ENDPOINT)
                    .create(ServiceEndpoint::class.java)
            return ServiceRepositoryImpl(ServiceWrapper(create))
        }
    }

    override fun fetchRepositories(repositoryRequest: RepositoryRequest): LiveData<RepositorieDTO> {
        serviceWrapper.search(repositoryRequest)
        return valueObserver
    }

    override fun fetchPullRequest(user: String, repository: String) {

    }

}