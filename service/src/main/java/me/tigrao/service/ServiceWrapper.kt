package me.tigrao.service

import android.arch.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


internal class ServiceWrapper constructor(private val api: ServiceEndpoint) {

    lateinit var valueObserver: MutableLiveData<RepositorieDTO>
    lateinit var errorObserver: MutableLiveData<String>

    fun search(language: String, sort: String, page: Int) {
        api.search(language, sort, page).enqueue(object : InnerCallback<RepositorieDTO>() {

            override fun onResponse(call: Call<RepositorieDTO>, response: Response<RepositorieDTO>) {
                EnqueueHandler(valueObserver, errorObserver).handler(response)
            }

        })
    }

    private abstract class InnerCallback<T> : Callback<T> {

        override fun onFailure(call: Call<T>?, t: Throwable?) {

        }

    }

}