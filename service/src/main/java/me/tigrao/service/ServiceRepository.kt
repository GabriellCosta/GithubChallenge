package me.tigrao.service

import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface ServiceRepository {

    fun fetchRepositories(language: String, sort: String, page: Int)

    fun fetchPullRequest(user: String, repositoy: String)

}

class ServiceRepositoryImpl internal constructor(private val api: ServiceEndpoint): ServiceRepository {

    val valueObserver: MutableLiveData<RepositorieDTO> = MutableLiveData()
    val errorObserver: MutableLiveData<String> = MutableLiveData()

    companion object {

        private const val GITHUB_ENDPOINT = "http://api.github.com"

        fun newInstance(): ServiceRepository {
            val create = RetrofitBuilder.build(GITHUB_ENDPOINT)
                    .create(ServiceEndpoint::class.java)
            return ServiceRepositoryImpl(create)
        }
    }

    override fun fetchRepositories(language: String, sort: String, page: Int) {
        api.search(language, sort, page).enqueue(object : InnerCallback<RepositorieDTO>() {

            override fun onResponse(call: Call<RepositorieDTO>, response: Response<RepositorieDTO>) {
                if (response.isSuccessful)
                    valueObserver.value = response.body()
                else
                    errorObserver.value = "Sem valor"
            }

        })
    }

    override fun fetchPullRequest(user: String, repositoy: String) {
        api.fetchPullRequests(user, repositoy)
                .enqueue(object : InnerCallback<List<PullRequestVO>>() {
                    override fun onResponse(call: Call<List<PullRequestVO>>?, response: Response<List<PullRequestVO>>?) {

                    }

                })
    }

    private abstract class InnerCallback<T>: Callback<T> {

        override fun onFailure(call: Call<T>?, t: Throwable?) {

        }

    }


}