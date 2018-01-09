package me.tigrao.service

import android.arch.lifecycle.MutableLiveData
import retrofit2.Response


internal class EnqueueHandler<T>(private val valueObserver: MutableLiveData<T>,
                        private val errorObserver: MutableLiveData<String>) {

    companion object {
        const val ERROR_MESSAGE = "Fail to load list"
    }

    fun handler(response: Response<T>) =
            if (response.isSuccessful) valueObserver.value = response.body()
            else errorObserver.value = ERROR_MESSAGE

}

